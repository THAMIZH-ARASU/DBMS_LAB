select * from TAB;


desc inventory

select * from inventory

SELECT STOCK_ID, LOCATION, QUANTITY,
       CASE 
           WHEN LOCATION = 'Warehouse A' THEN QUANTITY + 3000
           WHEN LOCATION = 'Warehouse B' THEN QUANTITY + 5000
           WHEN LOCATION = 'Warehouse C' THEN QUANTITY + 7000
           ELSE QUANTITY 
       END AS REVISED_QUANTITY
FROM INVENTORY;


SELECT STOCK_ID, LOCATION, QUANTITY,
       DECODE(LOCATION, 
              'Warehouse A', QUANTITY + 5000,
              'Warehouse B', QUANTITY + 3000,
              'Warehouse C', QUANTITY + 8000,
              QUANTITY) AS REVISED_QUANTITY
FROM INVENTORY;