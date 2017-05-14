create table stocks(
    stock_id int,
    company_name varchar(50),
    symbol varchar(10),
    price decimal(10,2)
);

insert into stocks(stock_id, company_name, symbol, price) values(1, 'Comp1', 'C1', 10.2);
insert into stocks(stock_id, company_name, symbol, price) values(2, 'Comp2', 'C2', 1.5);
insert into stocks(stock_id, company_name, symbol, price) values(3, 'Comp3', 'C3', 23.6);