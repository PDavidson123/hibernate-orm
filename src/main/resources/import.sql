create database UserSchema;
use UserSchema;

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