create table stocks(
    stockId int,
    companyName varchar2(50),
    symbol varchar2(10),
    price decimal(10,2)
);

insert into stocks(stockId, companyName, symbol, price) values(1, 'Comp1', 'C1', 10.2);
insert into stocks(stockId, companyName, symbol, price) values(2, 'Comp2', 'C2', 1.5);
insert into stocks(stockId, companyName, symbol, price) values(3, 'Comp3', 'C3', 23.6);