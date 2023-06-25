create database demo;

use demo;

CREATE TABLE `users`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(48) NOT NULL,
    `password` VARCHAR(48) NOT NULL,
    `enabled` INT NOT NULL,
    PRIMARY KEY(`id`)
);

CREATE TABLE `authorities`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(48) NOT NULL,
    `authority` VARCHAR(48) NOT NULL,
    PRIMARY KEY(`id`)
);

INSERT IGNORE INTO `users` VALUES(NULL, 'happy','12345',1);
INSERT IGNORE INTO `authorities` VALUES(NULL,'happy','write');

create table `customer`(
	`id` int not null primary key auto_increment,
    `email` varchar(48) not null,
    `pwd` varchar(48) not null,
    `role` varchar(48) not null
);

insert into `customer`(`email`,`pwd`,`role`) values ('john@example.com','123','admin');