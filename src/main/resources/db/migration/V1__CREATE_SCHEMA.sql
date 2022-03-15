create table car
(
	id uuid not null primary key,
	name varchar(255) not null,
	price int not null,
	category varchar(255) not null
);

create table client
(
	id uuid not null primary key,
	email varchar(255) not null
);

create table carOrder
(
    id uuid not null primary key,
    email varchar(255) not null,
    price int not null,
    carId int not null
);