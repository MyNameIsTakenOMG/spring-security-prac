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