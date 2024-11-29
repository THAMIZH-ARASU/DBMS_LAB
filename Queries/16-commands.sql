CREATE TABLE Items (
    item_id      INT PRIMARY KEY,
    item_name    VARCHAR(100) NOT NULL,
    category     varchar2(100) NOT NULL,
    brand        VARCHAR2(100) NOT NULL,
    model        varchar2 (100) NOT NULL,
    specification varchar2(100) NOT NULL,
    price         FLOAT(8) NOT NULL,
    interested number(6)
);
CREATE TABLE INVENTORY (
    STOCK_ID INT PRIMARY KEY,
    ITEM_ID INT NOT NULL,
    IN_DATE DATE NOT NULL,
    QUANTITY INT NOT NULL 
);
DESC Items;

INSERT INTO Items values(1234,'iphone','phone','apple','13','xyz',50000,5);
INSERT INTO Items values(1235,'f19 PRO PLUS','phone','OPPO','F19','xyz',22000,30);

SELECT * FROM Items;

ALTER TABLE Items ADD (supplier VARCHAR2(100));


ALTER TABLE Items ADD (status VARCHAR2(50) DEFAULT 'AVAILABLE');
SELECT * FROM Items;

ALTER TABLE Items MODIFY (price FLOAT(10));
DESC Items;


ALTER TABLE Items ADD CONSTRAINT check_price CHECK (price > 0);
DESC Items;

ALTER TABLE Items DROP COLUMN status;
DESC Items;


ALTER TABLE Items RENAME COLUMN item_name TO product_name;
DESC Items;


UPDATE Items SET price = 65000 WHERE item_id = 1234;
SELECT * FROM Items;


INSERT INTO INVENTORY (stock_id, item_id, in_date, quantity) 
	VALUES (1003, 1235, TO_DATE('2024-10-17', 'YYYY-MM-DD'), 50);
SELECT * FROM INVENTORY;







