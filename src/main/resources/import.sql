create database UserSchema;
use UserSchema;

create table User(userID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name varchar(50),
    password varchar(50));
insert into User values(1,'dawe','sziauram123');
insert into User values(2,'sanyika','oops123');

create table Address(addressID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userID bigint,
    city varchar(50),
    roadName varchar(50),
    houseNumber int,
    FOREIGN KEY (userID) REFERENCES User(userID));
insert into Address values(1,2,'Budapest', 'Kicsi utca',13);

create table Product(productID bigint NOT NULL PRIMARY KEY  AUTO_INCREMENT,
    userID bigint,
    name varchar(30),
    description varchar(200),
    price int,
    FOREIGN KEY (userID) REFERENCES User(userID));

create table Login(loginID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userID bigint,
    dateTime DATETIME,
    FOREIGN KEY (userID) REFERENCES User(userID));

create table TokenHash(tokenID bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    token bigint,
    expTime DATETIME);