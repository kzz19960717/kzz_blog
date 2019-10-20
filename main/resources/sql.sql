create database k_blog;
use k_blog;
create table user(uid INT AUTO_INCREMENT COMMENT '用户id',username VARCHAR(20) NOT NULL UNIQUE COMMENT '用户名',password CHAR(32) NOT NULL COMMENT '密码',phone VARCHAR(20) COMMENT '电话号码',email VARCHAR(30) COMMENT '电子邮箱',avatar VARCHAR(100) COMMENT '头像',is_delete INT COMMENT '是否删除：0-未删除，1-已删除',PRIMARY KEY (uid) );
create table doc(uid INT AUTO_INCREMENT,name VARCHAR(20) ,email VARCHAR(30) ,openEmail INT ,github VARCHAR(20) ,weibo varchar(20),userHomePage varchar(20),instructor varchar(100),PRIMARY KEY (uid)  );
create table article(uid INT AUTO_INCREMENT,title varchar(50) not null , mainBody varchar(50) not null,createdDate varchar(20), classname varchar(10),tag varchar(10),isDelete int,draft int,username varchar(20) ,PRIMARY KEY (uid));
create table class(id int AUTO_INCREMENT,name varchar(10),count int,PRIMARY KEY (id) );
create table tag(id int AUTO_INCREMENT,name varchar(10),count int ,PRIMARY KEY (id) );



