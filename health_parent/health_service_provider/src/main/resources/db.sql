create database health character set utf8;

use health;

create table user(
    id int not null primary key auto_increment,
    username varchar(20) not null unique ,
    password varchar(20) not null
);