create database user_info2;
use user_info2;
create table user_basc(user_id varchar(15) primary key not null,user_password varchar(15) not null,user_sex char(2) );

create table goods(goods_id varchar(20) primary key not null,
                   goods_price float(10,2) not null,
                   goods_type varchar(20) not null,
                   goods_description varchar(300),
                   goods_imgSrc varchar(50),
                   goods_sellCount int);

create table user_order(order_id int primary key not null auto_increment,
                        goods_id varchar (20) not null,
                        user_id varchar(15) not null);

create table shoppingCar(shopping_id int primary key not null auto_increment,
                         goods_id varchar(20) not null,
                         user_id varchar(15) not null);