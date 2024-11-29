-- Create table for storing items (products)
CREATE TABLE Items (
    item_id NUMBER PRIMARY KEY, -- Primary Key: item ID
    item_name VARCHAR2(255) NOT NULL, -- Name of the item (product)
    category VARCHAR2(255), -- Category of the item
    brand VARCHAR2(255), -- Brand name
    model VARCHAR2(255), -- Model of the item
    specification VARCHAR2(255), -- Product specification details
    price NUMBER(10, 2), -- Price of the item
    interested NUMBER(10) DEFAULT 0 -- User interest count (for restocking decision)
);


SELECT * FROM Items;


--BETWEEN
SELECT item_name, price
FROM Items
WHERE price BETWEEN 500 AND 1500;


--IN
SELECT item_id, item_name, interested 
FROM Items
WHERE category IN ('Smartphone', 'Tablet');


--ROUND
SELECT item_id, item_name, ROUND(price) AS rounded_price
FROM Items WHERE item_id <= 5;


--TRUNCATE
SELECT item_id, item_name, TRUNC(price, 2) AS truncated_price
FROM Items;


--MOD
SELECT item_id, item_name
FROM Items
WHERE MOD(item_id, 2) = 1;


--ALIAS
SELECT item_name AS Product, price AS Cost
FROM Items;


--HAVING AND ORDER BY
SELECT category, SUM(interested) AS total_interest
FROM Items
GROUP BY category
HAVING SUM(interested) > 10
ORDER BY total_interest DESC;


--SUBQUERY WITH 'ANY'
SELECT item_name, category, price
FROM Items
WHERE price > ANY (SELECT price FROM Items WHERE category = 'Tablet');


--SUBQUERY WITH 'ALL'
SELECT item_name, category, price
FROM Items
WHERE price > ALL (SELECT price FROM Items WHERE category = 'Smartphone');


--COLUMN FORMAT FOR NUMBERS

SELECT item_id, item_name, TO_CHAR(price, '99999999') AS formatted_price
FROM Items WHERE item_id < 5;

SELECT item_id, item_name, TO_CHAR(price, '00999999') AS formatted_price
FROM Items WHERE item_id < 5;

SELECT item_id, item_name, TO_CHAR(price, '$99999999') AS formatted_price
FROM Items WHERE item_id < 5;

SELECT item_id, item_name, TO_CHAR(price, 'L99999999') AS formatted_price
FROM Items WHERE item_id < 5;

SELECT item_id, item_name, TO_CHAR(price, '999999.99') AS formatted_price
FROM Items WHERE item_id < 5;

SELECT item_id, item_name, TO_CHAR(price, '$009,999.99') AS formatted_price
FROM Items WHERE item_id < 5;


--LTRIM
SELECT item_id, LTRIM(model, '0123456789 ') AS trimmed_model
FROM Items;



--RTRIM
SELECT item_id, RTRIM(model, '0123456789 ') AS trimmed_model
FROM Items;

















create table example(
    id int primary key,
    name varchar2(10) not null
);

insert into example values(1, 'ram');
insert into example values(7, 'karthick');

create table example2(
    id int primary key,
    name varchar2(10) not null
);

insert into example2 (id, name) select id, name from example;

insert into example2 values(6, 'vijay');

select * from example;
select * from example2;


INSERT INTO example2 (col1, col2, col3)
SELECT e.id, e.name
FROM example e
WHERE NOT EXISTS (
    SELECT 1
    FROM example2 ex2
    WHERE ex2.id = e.id
);



UPDATE example e SET e.name = (SELECT e2.name FROM example2 e2 WHERE e.id = e2.id);
