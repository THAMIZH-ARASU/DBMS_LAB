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

desc items;
INSERT INTO Items values(1234,'iphone','phone','apple','13','xyz',50000,5);
SELECT * FROM Items;


CREATE TABLE INVENTORY (
    STOCK_ID INT PRIMARY KEY,
    ITEM_ID INT NOT NULL,
    IN_DATE DATE NOT NULL,
    QUANTITY INT NOT NULL 
);

DESC INVENTORY;
INSERT INTO INVENTORY VALUES(3456, 1234, TO_DATE('2024-09-25', 'YYYY-MM-DD'), 5);
SELECT * FROM INVENTORY;
