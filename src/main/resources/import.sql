create database hello;
use hello;
CREATE TABLE Book(id int NOT NULL PRIMARY KEY, name varchar(10), author varchar(20), pages int);
insert into Book values(1,'konyvpls','envagyokaz', 600);

create table User(userID int NOT NULL PRIMARY KEY, name varchar(10), password varchar(20));