SELECT * FROM Items;


--Aggregate functions
SELECT category, MAX(price) AS highest_price
FROM Items
GROUP BY category;


SELECT category, MIN(price) AS lowest_price
FROM Items
GROUP BY category;


SELECT brand, SUM(price) AS total_price
FROM Items
GROUP BY brand;


SELECT category, AVG(interested) AS avg_interest
FROM Items
GROUP BY category;


SELECT brand, COUNT(*) AS total_items
FROM Items
GROUP BY brand;

SELECT item_name AS first_item
FROM Items
ORDER BY item_id
FETCH FIRST 1 ROWS ONLY;

SELECT item_name AS first_item
FROM Items
ORDER BY item_id DESC
FETCH FIRST 1 ROWS ONLY;



--Sub Query
SELECT item_id, item_name, price
FROM Items
WHERE price > (SELECT AVG(price) FROM Items);

SELECT DISTINCT brand
FROM Items
WHERE brand IN (
    SELECT brand
    FROM Items
    GROUP BY brand
    HAVING COUNT(*) > 2
);

SELECT item_id, item_name, price
FROM Items
WHERE price > (
    SELECT AVG(price) FROM Items
);

SELECT item_id, item_name, price
FROM Items
WHERE price > (
    SELECT AVG(price) 
    FROM Items 
    WHERE category = 'Laptop'
);

SELECT item_id, item_name, price, brand
FROM Items
WHERE brand IN (
    SELECT brand
    FROM Items 
    WHERE item_id < 3
);


SELECT item_id, item_name, category
FROM Items
WHERE category IN (
    SELECT category
    FROM Items
    GROUP BY category
    HAVING COUNT(*) > 3
);






--String operations
SELECT UPPER(item_name) AS upper_item_name FROM Items;

SELECT LOWER(brand) AS lower_brand FROM Items;

SELECT model, LENGTH(model) AS model_length FROM Items;

SELECT category, SUBSTR(category, 1, 3) AS short_category FROM Items WHERE ITEM_ID < 5;

SELECT brand || '-' || model AS brand_model FROM Items;

SELECT TRIM(item_name) AS trimmed_item_name FROM Items where item_id = 12;

SELECT item_name AS with_ple
FROM Items
WHERE LOWER(item_name) LIKE '%ple%';

SELECT item_name AS with_ple
FROM Items
WHERE LOWER(item_name) LIKE '__ple%';


SELECT MID(item_name, 2, 2) from Items;



--SELECT LEFT(item_name, 4) AS left_item_name FROM Items;

--SELECT RIGHT(item_name, 3) AS right_item_name FROM Items;

--SELECT item_name, POSITION('Pro' IN item_name) AS position_pro FROM Items;
