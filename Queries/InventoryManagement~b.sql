-- Create the table `currentstocks`
CREATE TABLE currentstocks_b (
  productcode VARCHAR2(100) NOT NULL,
  quantity NUMBER(11) NOT NULL,
  CONSTRAINT pk_currentstocks_b PRIMARY KEY (productcode)
);

-- Insert data into `currentstocks`
INSERT INTO currentstocks (productcode, quantity) VALUES ('p2', 30);
INSERT INTO currentstocks (productcode, quantity) VALUES ('p1', 1);
INSERT INTO currentstocks (productcode, quantity) VALUES ('p10', 0);
INSERT INTO currentstocks (productcode, quantity) VALUES ('prod1', 1);
INSERT INTO currentstocks (productcode, quantity) VALUES ('prod5', 35);
INSERT INTO currentstocks (productcode, quantity) VALUES ('prod3', 0);
INSERT INTO currentstocks (productcode, quantity) VALUES ('prod2', 0);

-- Create the table `customers`
CREATE TABLE customers_b (
  cid NUMBER(11) NOT NULL,
  customercode VARCHAR2(100) NOT NULL,
  fullname VARCHAR2(50) NOT NULL,
  location VARCHAR2(50) NOT NULL,
  phone VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_customers_b PRIMARY KEY (cid)
);

CREATE SEQUENCE customers_b_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- Insert data into `customers`
INSERT INTO customers (cid, customercode, fullname, location, phone) VALUES (2, 'cus3', 'ram', 'ktm', '331');

-- Create the table `products`
CREATE TABLE products_b (
  pid NUMBER(11) NOT NULL,
  productcode VARCHAR2(100) NOT NULL,
  productname VARCHAR2(50) NOT NULL,
  costprice NUMBER(12, 2) NOT NULL,
  sellingprice NUMBER(12, 2) NOT NULL,
  brand VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_products_b PRIMARY KEY (pid),
  CONSTRAINT chk_price_b CHECK (sellingprice >= costprice)
);

CREATE SEQUENCE products_b_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- Insert data into `products`
INSERT INTO products (pid, productcode, productname, costprice, sellingprice, brand) VALUES (73, 'prod3', 'qq', 3, 2, '4d');
INSERT INTO products (pid, productcode, productname, costprice, sellingprice, brand) VALUES (72, 'prod2', 'pen', 20, 30, 'techno');
INSERT INTO products (pid, productcode, productname, costprice, sellingprice, brand) VALUES (71, 'prod1', 'wai wai', 400, 450, 'cg');
INSERT INTO products (pid, productcode, productname, costprice, sellingprice, brand) VALUES (74, 'prod4', 'wai wai', 400, 450, 'cg2');
INSERT INTO products (pid, productcode, productname, costprice, sellingprice, brand) VALUES (78, 'prod5', 'Mobile', 500, 700, 'cg');

-- Create the table `purchaseinfo`
CREATE TABLE purchaseinfo_b (
  purchaseid NUMBER(11) NOT NULL,
  suppliercode VARCHAR2(200) NOT NULL,
  productcode VARCHAR2(200) NOT NULL,
  purchase_date DATE DEFAULT SYSDATE, 
  quantity NUMBER(11) NOT NULL,
  totalcost NUMBER(12, 2) NOT NULL,
  CONSTRAINT pk_purchaseinfo_b PRIMARY KEY (purchaseid)
);

drop table

CREATE SEQUENCE purchaseinfo_b_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

select * from purchaseinfo_b;
select* from purchaseinfo;
update purchaseinfo set productcode = 'prod7' where purchaseid = 9;


MERGE INTO purchaseinfo_b e2 
USING purchaseinfo e 
ON (e2.purchaseid = e.purchaseid) 
WHEN MATCHED THEN UPDATE SET 
    e2.suppliercode = e.suppliercode,
    e2.productcode = e.productcode,
    e2.purchase_date = e.purchase_date,
    e2.quantity = e.quantity,
    e2.totalcost = e.totalcost
WHEN NOT MATCHED THEN INSERT (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (e.purchaseid, e.suppliercode, e.productcode, e.purchase_date, e.quantity, e.totalcost);


-- WHERE NOT EXISTS (
--    SELECT 1 FROM purchaseinfo_b e3 WHERE e3.purchaseid = e.purchaseid
--);


-- If the column is of type DATE, use TO_DATE to insert a valid date format
-- Remove the timezone and weekday for simplicity
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (19, 's1', 'p2', TO_DATE('Jan 14 00:15:19 2015', 'Mon DD HH24:MI:SS YYYY'), 40, 1320);
-- Insert additional purchase records
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (20, 's2', 'p3', TO_DATE('2015-02-10 10:25:00', 'YYYY-MM-DD HH24:MI:SS'), 50, 1650);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (21, 's3', 'p4', TO_DATE('2016-05-21 14:40:15', 'YYYY-MM-DD HH24:MI:SS'), 30, 990);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (22, 's1', 'p5', TO_DATE('2017-09-30 08:15:30', 'YYYY-MM-DD HH24:MI:SS'), 60, 1800);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (23, 's4', 'p6', TO_DATE('2018-11-14 16:50:45', 'YYYY-MM-DD HH24:MI:SS'), 45, 1350);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (24, 's2', 'p7', TO_DATE('2019-03-22 11:10:30', 'YYYY-MM-DD HH24:MI:SS'), 70, 2100);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (25, 's5', 'p8', TO_DATE('2020-06-18 12:45:55', 'YYYY-MM-DD HH24:MI:SS'), 20, 600);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (26, 's3', 'p9', TO_DATE('2021-08-10 09:30:00', 'YYYY-MM-DD HH24:MI:SS'), 80, 2400);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (27, 's4', 'p10', TO_DATE('2022-12-05 13:20:40', 'YYYY-MM-DD HH24:MI:SS'), 50, 1500);

INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) 
VALUES (28, 's5', 'p11', TO_DATE('2023-02-19 17:05:10', 'YYYY-MM-DD HH24:MI:SS'), 65, 1950);


-- Insert data into `purchaseinfo`
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) VALUES (19, 's1', 'p2', 'Wed Jan 14 00:15:19 NPT 2015', 40, 1320);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) VALUES (20, 's1', 'p1', 'Wed Jan 07 16:42:44 NPT 2015', 4, 80000);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) VALUES (21, 's6', 'p10', 'Tue Jan 06 16:51:44 NPT 2015', 20, 400000);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) VALUES (22, 'sup4', 'prod1', 'Thu Jan 15 15:25:45 NPT 2015', 4, 1600);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) VALUES (23, 'sup5', 'prod1', 'Thu Jan 15 00:00:00 NPT 2015', 6, 2400);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) VALUES (24, 'sup4', 'prod5', 'Fri Jan 16 22:14:10 NPT 2015', 30, 15000);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, purchase_date, quantity, totalcost) VALUES (25, 'sup18', 'prod5', 'Sat Jan 17 22:23:52 NPT 2015', 5, 2500);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, date, quantity, totalcost) VALUES (26, 'sup18', 'prod3', 'Fri Jan 16 00:00:00 NPT 2015', 4, 12);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, date, quantity, totalcost) VALUES (27, 'sup18', 'prod2', 'Fri Jan 16 22:42:33 NPT 2015', 10, 200);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, date, quantity, totalcost) VALUES (28, 'sup71', 'prod1', 'Thu Jan 22 23:06:32 NPT 2015', 1, 600);
INSERT INTO purchaseinfo (purchaseid, suppliercode, productcode, date, quantity, totalcost) VALUES (29, 'sup4', 'prod2', 'Fri Jan 16 23:09:17 NPT 2015', 5, 150);

-- Create the table `salesreport`
CREATE TABLE salesreport_b (
  salesid NUMBER(11) NOT NULL,
  report_date DATE DEFAULT SYSDATE,
  productcode VARCHAR2(100) NOT NULL,
  customercode VARCHAR2(100) NOT NULL,
  quantity NUMBER(11) NOT NULL,
  revenue NUMBER(12, 2) NOT NULL,
  soldby VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_salesreport_b PRIMARY KEY (salesid)
);

CREATE SEQUENCE sales_report_b_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

select * from salesreport;
-- Insert data into `salesreport`
INSERT INTO salesreport (salesid, date, productcode, customercode, quantity, revenue, soldby) VALUES (1, 'Fri Jan 16 23:12:40 NPT 2015', 'prod2', 'cus3', 4, 120, 'user4');
INSERT INTO salesreport (salesid, date, productcode, customercode, quantity, revenue, soldby) VALUES (2, 'Thu Jan 08 21:30:51 NPT 2015', 'prod1', 'cus3', 5, 2250, 'sazanrjb');
INSERT INTO salesreport (salesid, date, productcode, customercode, quantity, revenue, soldby) VALUES (3, 'Thu Jan 15 21:26:47 NPT 2015', 'prod1', 'cus3', 5, 2250, 'sazanrjb');
INSERT INTO salesreport (salesid, date, productcode, customercode, quantity, revenue, soldby) VALUES (4, 'Sat Jan 17 10:08:20 NPT 2015', 'prod3', 'cus3', 1, 2, 'user4');

-- Insert sample sales reports
INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (1, TO_DATE('2023-11-01', 'YYYY-MM-DD'), 'P001', 'C001', 5, 500.00, 'John Doe');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (2, TO_DATE('2023-11-02', 'YYYY-MM-DD'), 'P002', 'C002', 3, 300.00, 'Jane Smith');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (3, TO_DATE('2023-11-03', 'YYYY-MM-DD'), 'P003', 'C003', 10, 1500.00, 'Alice Brown');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (4, TO_DATE('2023-11-04', 'YYYY-MM-DD'), 'P004', 'C004', 2, 200.00, 'Bob White');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (5, TO_DATE('2023-11-05', 'YYYY-MM-DD'), 'P005', 'C005', 8, 960.00, 'Charlie Green');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (6, TO_DATE('2023-11-06', 'YYYY-MM-DD'), 'P006', 'C006', 4, 400.00, 'Dana Black');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (7, TO_DATE('2023-11-07', 'YYYY-MM-DD'), 'P007', 'C007', 6, 720.00, 'Eve Blue');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (8, TO_DATE('2023-11-08', 'YYYY-MM-DD'), 'P008', 'C008', 12, 1440.00, 'Frank Yellow');

INSERT INTO salesreport (salesid, report_date, productcode, customercode, quantity, revenue, soldby) 
VALUES (9, TO_DATE('2023-11-09', 'YYYY-MM-DD'), 'P009', 'C009', 7, 1050.00, 'Grace Red');


-- Create the table `suppliers`
CREATE TABLE suppliers_b (
  sid NUMBER(11) NOT NULL,
  suppliercode VARCHAR2(100) NOT NULL,
  fullname VARCHAR2(50) NOT NULL,
  location VARCHAR2(50) NOT NULL,
  phone VARCHAR2(10) NOT NULL,
  CONSTRAINT pk_suppliers_b PRIMARY KEY (sid)
);

CREATE SEQUENCE suppliers_b_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


-- Insert data into `suppliers`
INSERT INTO suppliers (sid, suppliercode, fullname, location, phone) VALUES (69, 'sup5', 'manish', 'ktm', '4123372');
INSERT INTO suppliers (sid, suppliercode, fullname, location, phone) VALUES (68, 'sup4', 'sia', 'US', '11623231');
INSERT INTO suppliers (sid, suppliercode, fullname, location, phone) VALUES (67, 'sup3', 'shyam', 'bkt', '4121233');
INSERT INTO suppliers (sid, suppliercode, fullname, location, phone) VALUES (66, 'sup2', 'hari', 'pkr', '4121216');
INSERT INTO suppliers (sid, suppliercode, fullname, location, phone) VALUES (65, 'sup1', 'ram', 'ktm', '4284232');


CREATE TABLE users_b (
  id NUMBER(11) NOT NULL,
  fullname VARCHAR2(50) NOT NULL,
  location VARCHAR2(50) NOT NULL,
  phone VARCHAR2(10) NOT NULL,
  username VARCHAR2(20) NOT NULL,
  password VARCHAR2(200) NOT NULL,
  category VARCHAR2(20) NOT NULL,
  CONSTRAINT pk_users_b PRIMARY KEY (id)
);

CREATE SEQUENCE users_b_seq
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;


drop table users_b;

select * from users_b;

INSERT INTO users (id, fullname, location, phone, username, password, category) VALUES (users_seq.NEXTVAL, 'Thamizharasu', 'Puducherry', '9234567890', 'user1', '81dc9bdb52d04dc20036dbd8313ed055', 'ADMINISTRATOR');

SELECT * FROM users WHERE username='root' AND password='81dc9bdb52d04dc20036dbd8313ed055' AND category='admin' FETCH FIRST 1 ROWS ONLY;
