create database hello;
use hello;
CREATE TABLE Book(id int NOT NULL PRIMARY KEY AUTO_INCREMENT, name varchar(10), author varchar(20), pages int);
insert into Book values(1,'konyvpls','envagyokaz', 600);

create table User(userID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    password varchar(50));
insert into User values(1,'dawe','sziauram123');
insert into User values(2,'sanyika','oops123');

create table Address(addressID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userID int,
    city varchar(50),
    roadName varchar(50),
    houseNumber int,
    FOREIGN KEY (userID) REFERENCES User(userID));
insert into Address values(1,2,'Budapest', 'Kicsi utca',13);