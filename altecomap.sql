/*
If running the stated MySQL doesn't work then this can be run instead. 
WARNING: This is a clean install scenario. 
*/
USE ecomap;

DROP TABLE IF EXISTS `locations`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `problem_locations`;

CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1; 

CREATE TABLE `users` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

CREATE TABLE `problem_locations` (
	`id` int NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;
