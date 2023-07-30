--drop  table if exists city, users

create table city(
id serial8,
index int4 not null ,
name varchar not null ,
primary key (id)
);

create table users(
    id serial8,
    name varchar not null ,
    surname varchar not null ,
    login varchar not null ,
    password varchar not null ,
    city_id int8,
    primary key (id),
    FOREIGN KEY(city_id)REFERENCES city(id)
);