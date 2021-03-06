create table if not exists Ingredient (
id varchar(4) not null,
name varchar(25) not null,
type varchar(10) not null);

create table if not exists Taco (
id identity,
name varchar(50) not null,
createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
taco_id bigint not null,
ingredients_id varchar(4) not null
);

alter table Taco_Ingredients
add foreign key (taco_id) references Taco(id);
alter table Taco_Ingredients
add foreign key (ingredients_id) references Ingredient(id);

create table if not exists Taco_Order (
id identity,
deliveryName varchar(50) not null,
deliveryStreet varchar(50) not null,
deliveryCity varchar(50) not null,
deliveryState varchar(2) not null,
deliveryZip varchar(10) not null,
cc_number varchar(16) not null,
cc_expiration varchar(5) not null,
cc_cvv varchar(3) not null,
placed_at timestamp not null
);

create table if not exists Taco_Order_Tacos (
order_id bigint not null,
tacos_id bigint not null
);

alter table Taco_Order_Tacos
add foreign key (order_id) references Taco_Order(id);
alter table Taco_Order_Tacos
add foreign key (tacos_id) references Taco(id);

drop table if exists tacousers;

create table if not exists tacousers(
id identity,
username varchar(200) unique not null,
password varchar(100) not null,
fullname varchar(200) not null,
street varchar(200),
city varchar(100),
state varchar(50),
zip varchar(50));
