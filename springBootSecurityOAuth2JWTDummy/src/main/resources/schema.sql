drop table if exists authorities;
drop table if exists clients;

create table clients(
    id int not null primary key,
	email varchar(50) not null,
	encrypted_password varchar(256) not null,
    age int not null,
	enabled boolean not null
);

create table authorities (
    id int not null primary key,
	client_id int not null,
	authority varchar(50) not null,
	constraint fk_authorities_clients foreign key(client_id) references clients(id)
);

create unique index ix_clients_id_email on clients (id, email);
create unique index ix_authorities_id on authorities (id);
create index ix_authorities_client_id on authorities (client_id);


--password: uuu
insert into clients(id, email, encrypted_password, age, enabled) values(1, 'user@example.com', '$2a$10$de0dhctVMLtjGdRwU7IXXOXP34OKFgxIP.DGeo4xO./jNvIEAeLd2', 20, true);

--password: aaa
insert into clients(id, email, encrypted_password, age, enabled) values(2, 'admin@example.com', '$2a$10$5vBcKUpBNHsamIUdr5D4ouCN5ZyyI1GFx2Is28jw2QyTDC6lNKKrK', 30, true);

insert into authorities(id, client_id, authority) values(1,1,'ROLE_ANONYMOUS');
insert into authorities(id, client_id, authority) values(2,1,'ROLE_USER');
insert into authorities(id, client_id, authority) values(3,2,'ROLE_ANONYMOUS');
insert into authorities(id, client_id, authority) values(4,2,'ROLE_USER');
insert into authorities(id, client_id, authority) values(5,2,'ROLE_ADMIN');
