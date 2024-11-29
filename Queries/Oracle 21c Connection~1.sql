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

drop table items;

-- Create table for inventory (stock management)
CREATE TABLE Inventory (
    stock_id NUMBER PRIMARY KEY, -- Primary Key: Stock ID
    item_id NUMBER, -- Foreign Key: item_id from Items table
    in_date DATE DEFAULT SYSDATE, -- Date the item was added to inventory
    quantity NUMBER DEFAULT 0, -- Quantity available in stock
    FOREIGN KEY (item_id) REFERENCES Items(item_id) -- Foreign Key constraint
);

drop table inventory;
drop table LowStockNotifications;
drop table Sales;
-- Create table for tracking low stock notifications (optional)
CREATE TABLE LowStockNotifications (
    notification_id NUMBER PRIMARY KEY, -- Primary Key: Notification ID
    item_id NUMBER, -- Foreign Key: item_id from Items table
    quantity_threshold NUMBER, -- Threshold quantity that triggers restocking
    notification_date DATE DEFAULT SYSDATE, -- Date of notification
    FOREIGN KEY (item_id) REFERENCES Items(item_id) -- Foreign Key constraint
);

-- Example table for recording sales (optional for your requirements)
CREATE TABLE Sales (
    sale_id NUMBER PRIMARY KEY, -- Primary Key: Sale ID
    item_id NUMBER, -- Foreign Key: item_id from Items table
    sale_date DATE DEFAULT SYSDATE, -- Date of sale
    quantity_sold NUMBER, -- Quantity sold
    sale_price NUMBER(10, 2), -- Sale price of the item
    FOREIGN KEY (item_id) REFERENCES Items(item_id) -- Foreign Key constraint
);




-- Insert item into Items table
INSERT INTO Items (item_id, item_name, category, brand, model, specification, price)
VALUES (1, 'iPhone 14', 'Smartphone', 'Apple', '14', '128GB, Black', 999.99);


select * from Items;
drop table Items;
-- Insert item into Inventory table
INSERT INTO Inventory (stock_id, item_id, quantity)
VALUES (1, 1, 100);

-- Insert low stock notification (example)
INSERT INTO LowStockNotifications (notification_id, item_id, quantity_threshold)
VALUES (1, 1, 20);


INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (1, 'iPhone 15', 'Smartphone', 'Apple', '15 Pro Max', '6.7-inch display, 128GB storage', 1099.99, 50);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (2, 'Galaxy S24', 'Smartphone', 'Samsung', 'S24 Ultra', '6.8-inch display, 256GB storage', 1199.99, 60);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (3, 'MacBook Pro', 'Laptop', 'Apple', '14-inch', 'M1 Pro chip, 16GB RAM, 512GB SSD', 1999.99, 30);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (4, 'Dell XPS 13', 'Laptop', 'Dell', 'XPS 13', '13.4-inch display, Intel i7, 16GB RAM', 1599.99, 25);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (5, 'AirPods Pro', 'Earbuds', 'Apple', 'Pro 2nd Generation', 'Active Noise Cancellation, 20-hour battery life', 249.99, 100);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (6, 'Samsung Galaxy Tab S8', 'Tablet', 'Samsung', 'Tab S8', '11-inch display, 128GB storage', 699.99, 40);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (7, 'Sony WH-1000XM5', 'Headphones', 'Sony', 'WH-1000XM5', 'Noise Cancelling, 30-hour battery life', 349.99, 80);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (8, 'Apple Watch Series 8', 'Smartwatch', 'Apple', 'Series 8', 'GPS, 41mm case, ECG app', 399.99, 120);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (9, 'Acer Predator Helios 300', 'Laptop', 'Acer', 'Helios 300', '15.6-inch display, RTX 3060, 16GB RAM', 1499.99, 50);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (10, 'HP Spectre x360', 'Laptop', 'HP', 'Spectre x360 14', '13.5-inch display, Intel i7, 16GB RAM', 1699.99, 20);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (11, 'HP Spectre x360', 'Laptop', 'HP', 'Spectre x360 14', '13.5-inch display, Intel i7, 16GB RAM', 1699.99, 20);

INSERT INTO Items (item_id, item_name, category, brand, model, specification, price, interested) 
VALUES (12, '   HP    Spectre   x360   ', 'Laptop', 'HP', 'Spectre x360 14', '13.5-inch display, Intel i7, 16GB RAM', 1699.99, 20);

CREATE TABLE test_table (
  id NUMBER,
  name VARCHAR2(100)
);

INSERT INTO test_table (id, name) VALUES (1, 'Test');
INSERT INTO test_table (id, name) VALUES (2, 'Sample');

select * from items;

CREATE SEQUENCE item_seq
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;



CREATE TABLE PRODUCTS (
    ITEM_ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
    ITEM_NAME VARCHAR2(255),
    CATEGORY VARCHAR2(255),
    BRAND VARCHAR2(255),
    MODEL VARCHAR2(255),
    SPECIFICATION VARCHAR2(255),
    PRICE NUMBER(10, 2),
    INTERESTED NUMBER
);

drop table products;


INSERT INTO PRODUCTS (ITEM_NAME, CATEGORY, BRAND, MODEL, SPECIFICATION, PRICE, INTERESTED) 
VALUES ('iPhone 14', 'Mobile Phone', 'Apple', 'A2634', '128GB, Blue', 999.99, 5);

INSERT INTO PRODUCTS (ITEM_NAME, CATEGORY, BRAND, MODEL, SPECIFICATION, PRICE, INTERESTED) 
VALUES ('Galaxy S23', 'Mobile Phone', 'Samsung', 'SM-S911B', '256GB, Phantom Black', 799.99, 8);

INSERT INTO PRODUCTS (ITEM_NAME, CATEGORY, BRAND, MODEL, SPECIFICATION, PRICE, INTERESTED) 
VALUES ('MacBook Pro', 'Laptop', 'Apple', 'A2485', 'M2, 16GB RAM, 512GB SSD', 2399.99, 3);

INSERT INTO PRODUCTS (ITEM_NAME, CATEGORY, BRAND, MODEL, SPECIFICATION, PRICE, INTERESTED) 
VALUES ('ThinkPad X1 Carbon', 'Laptop', 'Lenovo', '20U30024US', '11th Gen i7, 16GB RAM, 512GB SSD', 1499.99, 4);

INSERT INTO PRODUCTS (ITEM_NAME, CATEGORY, BRAND, MODEL, SPECIFICATION, PRICE, INTERESTED) 
VALUES ('Pixel 7', 'Mobile Phone', 'Google', 'GVU6C', '128GB, Snow', 599.99, 6);


