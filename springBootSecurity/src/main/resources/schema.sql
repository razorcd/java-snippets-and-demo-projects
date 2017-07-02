drop table if exists authorities;
drop table if exists clients;

create table clients(
    id int not null primary key,
	email varchar(50) not null,
	encrypted_password varchar(50) not null,
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


insert into clients(id, email, encrypted_password, age, enabled) values(1, 'user@example.com', 'uuu', 20, true);
insert into clients(id, email, encrypted_password, age, enabled) values(2, 'admin@example.com', 'aaa', 30, true);

insert into authorities(id, client_id, authority) values(1,1,'ROLE_ANONYMOUS');
insert into authorities(id, client_id, authority) values(2,1,'ROLE_USER');
insert into authorities(id, client_id, authority) values(3,2,'ROLE_ANONYMOUS');
insert into authorities(id, client_id, authority) values(4,2,'ROLE_USER');
insert into authorities(id, client_id, authority) values(5,2,'ROLE_ADMIN');
