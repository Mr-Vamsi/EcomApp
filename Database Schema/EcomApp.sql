create Database EcomDB;
use EcomDB;
CREATE TABLE usersData (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    useremail VARCHAR(45) NOT NULL,
    usermobile VARCHAR(10) NOT NULL,
    userpassword VARCHAR(255) NOT NULL);
    select * from users_Data;    
    create table products_Data(
    sku int auto_increment primary key,
    productname varchar(50) not null,
    productdescription varchar(50) not null,
    productquantity varchar(50) not null,
    productcost varchar(50) not null
    )
    select * from products_Data;
    drop table products_Data;
    create table cart(
    id int auto_increment primary key,
    productname varchar(50) not null,
    productquantity varchar(50) not null,
    productcost varchar(50) not null,
    status varchar(50) not null
    )
    select * from cart;
    drop table cart;