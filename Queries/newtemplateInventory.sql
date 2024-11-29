
CREATE TABLE Items (
    item_id         NUMBER(8) GENERATED AS IDENTITY PRIMARY KEY,  -- Auto-increment using IDENTITY
    item_name       VARCHAR2(100) NOT NULL,
    category        VARCHAR2(100) NOT NULL,
    brand           VARCHAR2(100) NOT NULL,
    model           VARCHAR2(100) NOT NULL,
    specification   VARCHAR2(100) NOT NULL,
    price           NUMBER(8, 2) NOT NULL CHECK (price >= 0), 
    stock_threshold NUMBER(3) DEFAULT 10,
    interested      NUMBER(6) DEFAULT 0 CHECK (interested >= 0),  
    UNIQUE (item_name, brand, model) 
);

-- Insert records into the Items table


INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('ThinkPad X1 Carbon', 'Laptop', 'Lenovo', 'Gen 9', '14 inch, 16GB RAM', 1299.00, 10, 0);

INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('MacBook Pro', 'Laptop', 'Apple', 'M1 2020', '13 inch, 8GB RAM', 1499.00, 10, 0);

INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('Pixel 6', 'Smartphone', 'Google', 'G20', '128GB, 5G', 599.99, 10, 0);

INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('Dell XPS 13', 'Laptop', 'Dell', '9310', '13 inch, 16GB RAM', 1399.00, 10, 0);

INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('Surface Laptop 4', 'Laptop', 'Microsoft', '13.5 inch', '16GB RAM', 1299.99, 10, 0);

INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('iPad Air', 'Tablet', 'Apple', '4th Gen', '10.9 inch, 64GB', 599.99, 10, 0);

INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('Galaxy Tab S7', 'Tablet', 'Samsung', 'SM-T870', '11 inch, 128GB', 649.99, 10, 0);

INSERT INTO Items (item_name, category, brand, model, specification, price, stock_threshold, interested) 
VALUES ('HP Spectre x360', 'Laptop', 'HP', '13-AW2001', '13.3 inch, 16GB RAM', 1399.00, 10, 0);


CREATE TABLE Inventory (
    stock_id   NUMBER(8) GENERATED AS IDENTITY PRIMARY KEY, 
    item_id    NUMBER(8) NOT NULL,  
    in_date    DATE DEFAULT SYSDATE NOT NULL, 
    quantity   NUMBER(6) NOT NULL CHECK (quantity >= 0), 
    location   VARCHAR2(100) NOT NULL, 
    expiry_date DATE ,  
    item_reference VARCHAR2(100) 
);

-- Insert records into the Inventory table


INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (2, 60, 'Warehouse B', NULL, 'Galaxy S21 - 256GB');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (2, 60, 'Warehouse B', NULL, 'Galaxy S21 - 256GB');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (3, 10, 'Warehouse C', TO_DATE('2025-10-10', 'YYYY-MM-DD'), 'ThinkPad X1 Carbon - Gen 9');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (4, 25, 'Warehouse A', TO_DATE('2026-05-15', 'YYYY-MM-DD'), 'MacBook Pro - M1 2020');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (5, 5, 'Warehouse B', NULL, 'Pixel 6 - 128GB');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (6, 20, 'Warehouse C', NULL, 'Dell XPS 13 - 9310');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (7, 15, 'Warehouse D', TO_DATE('2026-01-01', 'YYYY-MM-DD'), 'Surface Laptop 4 - 13.5"');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (8, 40, 'Warehouse A', NULL, 'iPad Air - 4th Gen');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (9, 10, 'Warehouse B', NULL, 'Galaxy Tab S7 - Mystic Black');

INSERT INTO Inventory (item_id, quantity, location, expiry_date, item_reference) 
VALUES (10, 12, 'Warehouse A', NULL, 'HP Spectre x360 - 13-AW2001');



-- SELECT STATEMENTS FOR TABLES

SELECT * FROM Items;


SELECT * FROM INVENTORY;


--SET OPERATIONS

--UNION
SELECT item_id, category AS description FROM Items
UNION
SELECT item_id, location AS description FROM Inventory;

--UNION ALL
SELECT item_id, category AS description FROM Items
UNION ALL
SELECT item_id, location AS description FROM Inventory;


--INTERSECTION
SELECT item_id AS description FROM Items
INTERSECT
SELECT item_id AS description FROM Inventory;


--DIFFERRENCE
SELECT item_id, category AS description FROM Items
MINUS
SELECT item_id, location AS description FROM Inventory;



--RELATIONAL OPERATORS

--GREATER THAN OR EQUAL TO
SELECT item_id, item_name, price 
FROM Items 
WHERE price >= 1000;


--LESS THAN OR EQUAL TO
SELECT item_id, item_name, price 
FROM Items 
WHERE price <= 2000;


--EQUAL TO 
SELECT * FROM Items WHERE BRAND = 'Apple';


--ORDER BY COMMANDS

--ASCENDING
SELECT item_id, item_name, price 
FROM Items 
ORDER BY price ASC;

--DESCENDING
SELECT item_id, item_name, price 
FROM Items 
ORDER BY price DESC;

--GROUP BY COMMAND
SELECT category, COUNT(*) AS item_count 
FROM Items 
GROUP BY category;

