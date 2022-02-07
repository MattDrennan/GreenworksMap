/*
If running the stated MySQL doesn't work then this can be run instead. 
*/
USE ecomap;

DROP TABLE IF EXISTS `locations`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `locations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `iconid` int NOT NULL,
  `address` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `coord` varchar(255) NOT NULL,
  `dateStart` datetime DEFAULT NULL,
  `dateEnd` datetime DEFAULT NULL,
  `content` text,
  `api` tinyint(1) DEFAULT NULL,
  `website` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb3; 

CREATE TABLE `users` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(30) DEFAULT NULL,
  `username` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;