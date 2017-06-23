
-- Flyway migration files naming:
--   V1.1__init_schema.sql  - versioned
--   V1_1__init_schema.sql
--   R1.1__init_schema.sql  - repeateable, executed each time the file changes. Good for data init or regenerated views ...

-- Migrations can be written in SQL, Java, access Spring Context, read files, etc



create table stocks(
    stock_id int,
    company_name varchar(50),
    symbol varchar(10),
    price decimal(10,2)
);


