-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: orlando eco-map
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `buildings`
--

DROP TABLE IF EXISTS `buildings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buildings` (
  `sp_id` int NOT NULL,
  `loc_id` int NOT NULL,
  `street_address` varchar(250) DEFAULT NULL,
  `loc_descr` varchar(250) NOT NULL,
  `zip_code` int DEFAULT '99',
  PRIMARY KEY (`sp_id`,`loc_id`),
  CONSTRAINT `buildings_ibfk_1` FOREIGN KEY (`sp_id`) REFERENCES `sub_pillars` (`sub_pillar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buildings`
--

LOCK TABLES `buildings` WRITE;
/*!40000 ALTER TABLE `buildings` DISABLE KEYS */;
/*!40000 ALTER TABLE `buildings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `energy`
--

DROP TABLE IF EXISTS `energy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `energy` (
  `sp_id` int NOT NULL,
  `loc_id` int NOT NULL,
  `street_address` varchar(250) DEFAULT NULL,
  `loc_descr` varchar(250) NOT NULL,
  `zip_code` int DEFAULT '99',
  PRIMARY KEY (`sp_id`,`loc_id`),
  CONSTRAINT `energy_ibfk_1` FOREIGN KEY (`sp_id`) REFERENCES `sub_pillars` (`sub_pillar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `energy`
--

LOCK TABLES `energy` WRITE;
/*!40000 ALTER TABLE `energy` DISABLE KEYS */;
INSERT INTO `energy` VALUES (11,109,'512 Washington St','Lake Eola Park',32801),(11,300,'1224 Dorchester Street','Lake Estelle Park',32803),(11,309,'57 S Ivanhoe Boulevard','Lake Ivanhoe Park',32803),(11,316,'Lake Highland','Lake Highland Park',32803),(11,1000,'2200 Lee Road','Lake Fairview Park',32810);
/*!40000 ALTER TABLE `energy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `loc_code` int NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `event_name` varchar(250) NOT NULL,
  `purpose` varchar(250) NOT NULL,
  PRIMARY KEY (`event_id`),
  KEY `loc_code` (`loc_code`),
  CONSTRAINT `events_ibfk_1` FOREIGN KEY (`loc_code`) REFERENCES `locations` (`loc_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `sp_id` int NOT NULL,
  `loc_id` int NOT NULL,
  `street_address` varchar(250) DEFAULT NULL,
  `loc_descr` varchar(250) NOT NULL,
  `zip_code` int DEFAULT '99',
  PRIMARY KEY (`sp_id`,`loc_id`),
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`sp_id`) REFERENCES `sub_pillars` (`sub_pillar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (7,62842,'406 East Amelia Street','Lake Eola Heights',32803),(7,62867,'1517 Lake Highland Drive','Colonialtown Community Garden',32803),(7,62883,'2911 East Robinson Street','Festival Park',32803),(7,62943,'1517 Lake Highland Drive','Colonialtown Community Garden',32803),(7,62976,'2911 East Robinson Street','Festival Park',32803),(7,63012,'6123 La Costa Drive','Englewood Park/Neighborhood Center',32807),(7,63036,'1226 Elmwood Street','Lake Davis/Greenwood Cemetery',32801),(7,63079,'8495 Laureate Boulevard','Laureate Park I – resident only',32827),(7,63101,'9661 Tavistock Lakes Boulevard','Laureate Park II – resident only',32827),(7,63126,'3955 W D Judge Drive','Northwest Community Center',32808),(7,63151,'654 West Robinson Street','Parramore',32801),(7,63173,'3600 Rogers Drive','Washington Shores',32805),(7,63192,'440 North Tampa Avenue','Rock Lake Neighborhood Center',32805),(7,63227,'1400 Gaston Foster Road','1400 Gaston Foster Road',32812),(7,63271,'1935 Conway Road','Mai Kai Condominiums – resident only',32812);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livability`
--

DROP TABLE IF EXISTS `livability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livability` (
  `sp_id` int NOT NULL,
  `loc_id` int NOT NULL,
  `street_address` varchar(250) DEFAULT NULL,
  `loc_descr` varchar(250) NOT NULL,
  `zip_code` int DEFAULT '99',
  PRIMARY KEY (`sp_id`,`loc_id`),
  CONSTRAINT `livability_ibfk_1` FOREIGN KEY (`sp_id`) REFERENCES `sub_pillars` (`sub_pillar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livability`
--

LOCK TABLES `livability` WRITE;
/*!40000 ALTER TABLE `livability` DISABLE KEYS */;
INSERT INTO `livability` VALUES (10,67140,'1808 E Central Boulevard','Langford Park',32802),(10,67172,'100 Rosearden Drive','Dickson Azalea Park',32803),(10,67200,'300 S Summerlin Ave','Constitution Green Park',32801),(10,67223,'512 East Washington Street','Lake Eola Park',32801),(10,67249,'930 N Thornton Avenue','Big Tree Park',32803),(10,67281,'1920 North Forest Avenue','Harry P. Leu Gardens',32803),(10,67508,'777 E Princeton Street','Loch Haven Park',32803),(10,67548,'7098 Shadowridge Drive','Airport Lakes Park',32812),(10,67587,'901 Delaney Avenue','Al Coith Park',32806),(10,67612,'2400 Edgewater Drive','Albert Park',32804),(10,67634,'930 N Thornton Avenue','Big Tree Park',32803),(10,67659,'3401 South Hiawassee Road','Bill Frederick Park At Turkey Lake',32835),(10,67691,'2500 South Mills Avenue','Blankner Field',32806),(11,67760,'2501 General Rees Avenue','Blue Jacket Park',32814),(11,67787,'Cady Way Trail','Cady Way Trail',32803),(11,67809,'101 North Parramore Avenue','Callahan Neighborhood Center',32801),(11,67831,'619 Parramore Avenue','Checker Park',32801),(11,67936,'525 Cherokee Drive','Cherokee Park',32801),(11,67958,'2725 E Jackson Street','CCherry Tree Park',32803),(11,67999,'5624 Hickey Drive','Citrus Square Neighborhood Center',32822),(11,68027,'2301 29th Street','Clear Lake Park',32805),(11,68076,'305 S Crystal Lake Drive','Colonel Joe Kittinger Park',32803),(11,68099,'1517 Lake Highland Drive','Colonialtown Neighborhood Center',32803),(11,68125,'820 N Fern Creek Avenue','Colonialtown Square Park',32803),(11,68159,'300 S Summerlin Ave','Constitution Green',32801),(11,68185,'822 Dartmouth Street','Dartmouth Park',32804),(11,68205,'1055 Delaney Avenue','Delaney Park',32801),(11,68254,'650 Santiago Avenue','Demetree Park',32807),(11,68283,'100 Rosearden Drive','Dickson Azalea Park',32803),(11,68310,'851 Edgewater Drive','Don Dudley Park',32804),(11,68334,'1400 Gaston Foster Rd','Dover Shores Pool/Neighborhood Center',32812),(11,68368,'1340 Lake Park Court','Dr. I Sylvester Hankins Park Center',32805),(11,68402,'1723 Bruton Boulevard','Dr. James R. Smith Neighborhood Center',32805),(11,68431,'5165 Metrowest Boulevard','Eagle Nest Park',32811),(11,68464,'10193 Lake District Laned','East Lake Park',32832),(11,68506,'4400 West Colonial Drive','Emery Hamilton Sports Complex',32808),(11,68562,'6123 La Costa Dr','Engelwood Pool/Neighborhood Center',32807),(11,68786,'6050 Lake Underhill Rd','6050 Lake Underhill Rd',32807),(11,68925,'2911 E Robinson St','Festival Park',32803),(11,68966,'1236 N Orange Avenue','Gaston Edwards Park',32804),(11,69012,'2000 Monte Carlo Trail','George Barker Park',32805),(11,69135,'650 W Lake Mann Drive','Gilbert McQueen Park',32805),(11,69216,'700 Grand Street','Grand Avenue Park',32805),(11,69240,'1411 Greenwood Street','Greenwood Urban Wetlands',32801),(11,69324,'1320 Guernsey Street','Guernsey Park',32804),(11,69689,'1340 Lake Park Court','Hankins Park Pools',32805);
/*!40000 ALTER TABLE `livability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `loc_code` int NOT NULL,
  `loc_address` varchar(250) NOT NULL,
  `loc_zip` int DEFAULT NULL,
  PRIMARY KEY (`loc_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pillars`
--

DROP TABLE IF EXISTS `pillars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pillars` (
  `pillar_code` char(2) NOT NULL,
  `pillar_name` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`pillar_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pillars`
--

LOCK TABLES `pillars` WRITE;
/*!40000 ALTER TABLE `pillars` DISABLE KEYS */;
INSERT INTO `pillars` VALUES ('CE','Clean Energy'),('CW','Clean Water'),('EA','Electric & Alt. Transportation'),('GB','Green Buildings'),('LF','Local Food Systems'),('LV','Livability'),('ZW','Zero Waste');
/*!40000 ALTER TABLE `pillars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_pillars`
--

DROP TABLE IF EXISTS `sub_pillars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sub_pillars` (
  `pillar_code` char(2) NOT NULL,
  `sub_pillar_code` char(2) NOT NULL,
  `sub_pillar_id` int NOT NULL AUTO_INCREMENT,
  `sp_name` varchar(250) NOT NULL,
  PRIMARY KEY (`sub_pillar_id`),
  KEY `pillar_code` (`pillar_code`),
  CONSTRAINT `sub_pillars_ibfk_1` FOREIGN KEY (`pillar_code`) REFERENCES `pillars` (`pillar_code`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_pillars`
--

LOCK TABLES `sub_pillars` WRITE;
/*!40000 ALTER TABLE `sub_pillars` DISABLE KEYS */;
INSERT INTO `sub_pillars` VALUES ('CE','SA',1,'Solar Arrays'),('CE','RH',2,'Resilience Hubs'),('CE','ST',3,'Solar Tables of Connection'),('GB','CB',4,'LEED/Energy Star/Green Globe certified buildings'),('LF','FM',5,'Farmer\'s Markets'),('LF','CG',7,'Community Gardens'),('LF','UF',8,'Urban Farms'),('LF','AP',9,'Apiaries'),('LV','HT',10,'Historic Trees of Significance'),('LV','IN',11,'Iconic Parks and Neighborhood Centers'),('ZW','RF',12,'Recycling and Food Waste  Drop-offs'),('ZW','LF',13,'Landfill'),('ZW','HW',14,'Hazardous Waste Facility'),('ZW','RT',15,'Recycling Transfer Stations'),('CW','HS',16,'Hydration Stations'),('CW','OW',17,'Orlando Wet Parks'),('CW','WT',18,'Wastewater Treatment Plants'),('CW','PW',19,'Potable Water Plants'),('EA','EV',20,'EV Charging Stations'),('EA','BT',21,'Bike Trails'),('EA','BS',22,'Bus Stops');
/*!40000 ALTER TABLE `sub_pillars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport` (
  `sp_id` int NOT NULL,
  `loc_id` int NOT NULL,
  `street_address` varchar(250) DEFAULT NULL,
  `loc_descr` varchar(250) NOT NULL,
  `zip_code` int DEFAULT '99',
  PRIMARY KEY (`sp_id`,`loc_id`),
  CONSTRAINT `transport_ibfk_1` FOREIGN KEY (`sp_id`) REFERENCES `sub_pillars` (`sub_pillar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `waste`
--

DROP TABLE IF EXISTS `waste`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `waste` (
  `sp_id` int NOT NULL,
  `loc_id` int NOT NULL,
  `street_address` varchar(250) DEFAULT NULL,
  `loc_descr` varchar(250) NOT NULL,
  `zip_code` int DEFAULT '99',
  PRIMARY KEY (`sp_id`,`loc_id`),
  CONSTRAINT `waste_ibfk_1` FOREIGN KEY (`sp_id`) REFERENCES `sub_pillars` (`sub_pillar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `waste`
--

LOCK TABLES `waste` WRITE;
/*!40000 ALTER TABLE `waste` DISABLE KEYS */;
INSERT INTO `waste` VALUES (12,63987,'6123 La Costa Drive','Engelwood Neighborhood Center Drop-off',32807),(12,64011,'2200 Lee Road','Lake Fairview Park Drop-off',32810),(12,64038,'3955 WD Judge Road','Northwest Community Center Drop-off',32808),(12,64077,'400 Festival Way','Orlando Skate Park Drop-off',32803),(12,64103,'1028 South Woods Avenue','Solid Waste Management Division Drop-off',32805),(12,64129,'1400 Gaston Foster Rd','Dover Shores Community Center Drop-off',32812);
/*!40000 ALTER TABLE `waste` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `water`
--

DROP TABLE IF EXISTS `water`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `water` (
  `sp_id` int NOT NULL,
  `loc_id` int NOT NULL,
  `street_address` varchar(250) DEFAULT NULL,
  `loc_descr` varchar(250) NOT NULL,
  `zip_code` int DEFAULT '99',
  PRIMARY KEY (`sp_id`,`loc_id`),
  CONSTRAINT `water_ibfk_1` FOREIGN KEY (`sp_id`) REFERENCES `sub_pillars` (`sub_pillar_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `water`
--

LOCK TABLES `water` WRITE;
/*!40000 ALTER TABLE `water` DISABLE KEYS */;
/*!40000 ALTER TABLE `water` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'orlando eco-map'
--
/*!50003 DROP PROCEDURE IF EXISTS `SP_addEvent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_addEvent`(address VARCHAR(250), zip VARCHAR(250), starts_on DATE, ends_on DATE, e_name varchar(250), event_reason VARCHAR(250))
BEGIN
SET @code = (SELECT loc_code FROM locations WHERE loc_address AND loc_zip = address AND zip);
INSERT INTO events (loc_code, start_date, end_date, event_name, purpose) VALUES (@code, starts_on, ends_on, e_name, event_reason);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_buildings_loc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_buildings_loc`(sp_code CHAR(2), address VARCHAR(250), descr VARCHAR(250), zip INT)
BEGIN
CALL SP_sub_pillar_id(sp_code, "GB", @sp); -- change pillar id name
INSERT INTO buildings VALUES (@sp, TIME_TO_SEC(CURRENT_TIMESTAMP), address, descr, zip); -- change table name
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_energy_loc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_energy_loc`(sp_code CHAR(2), address VARCHAR(250), descr VARCHAR(250), zip INT)
BEGIN
CALL SP_sub_pillar_id(sp_code, "CE", @sp); -- change pillar id name
INSERT INTO energy VALUES (@sp, TIME_TO_SEC(CURRENT_TIMESTAMP), address, descr, zip); -- change table name
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_event_location` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_event_location`(address VARCHAR(250), zip INT)
BEGIN
INSERT INTO locations VALUES (CURRENT_TIMESTAMP(), address, zip);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_food_loc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_food_loc`(sp_code CHAR(2), address VARCHAR(250), descr VARCHAR(250), zip INT)
BEGIN
CALL SP_sub_pillar_id(sp_code, "LF", @sp); -- change pillar id name
INSERT INTO food VALUES (@sp, TIME_TO_SEC(CURRENT_TIMESTAMP), address, descr, zip); -- change table name
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_livability_loc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_livability_loc`(sp_code CHAR(2), address VARCHAR(250), descr VARCHAR(250), zip INT)
BEGIN
CALL SP_sub_pillar_id(sp_code, "LV", @sp); -- change pillar id name
INSERT INTO livability VALUES (@sp, TIME_TO_SEC(CURRENT_TIMESTAMP), address, descr, zip); -- change table name
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_transport_loc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_transport_loc`(sp_code CHAR(2), address VARCHAR(250), descr VARCHAR(250), zip INT)
BEGIN
CALL SP_sub_pillar_id(sp_code, "EA", @sp); -- change pillar id name
INSERT INTO transport VALUES (@sp, TIME_TO_SEC(CURRENT_TIMESTAMP), address, descr, zip); -- change table name
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_waste_loc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_waste_loc`(sp_code CHAR(2), address VARCHAR(250), descr VARCHAR(250), zip INT)
BEGIN
CALL SP_sub_pillar_id(sp_code, "ZW", @sp); -- change pillar id name
INSERT INTO waste VALUES (@sp, TIME_TO_SEC(CURRENT_TIMESTAMP), address, descr, zip); -- change table name
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_add_water_loc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_add_water_loc`(sp_code CHAR(2), address VARCHAR(250), descr VARCHAR(250), zip INT)
BEGIN
CALL SP_sub_pillar_id(sp_code, "CW", @sp); -- change pillar id name
INSERT INTO water VALUES (@sp, TIME_TO_SEC(CURRENT_TIMESTAMP), address, descr, zip); -- change table name
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_removeEvent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_removeEvent`()
DELETE FROM events WHERE end_date < CURRENT_DATE() ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_showBuildings` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_showBuildings`()
SELECT * FROM buildings ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_showEnergy` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_showEnergy`()
SELECT * FROM energy ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_showFood` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_showFood`()
SELECT * FROM food ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_showTransportation` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_showTransportation`()
SELECT * FROM transport ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_showWaste` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_showWaste`()
SELECT * FROM waste ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_showWater` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_showWater`()
SELECT * FROM water ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_sub_pillar_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_sub_pillar_id`(sp_code CHAR(2), p_code CHAR(2), out sp_id INT)
BEGIN
SELECT sub_pillar_id 
INTO sp_id
FROM sub_pillars 
WHERE sub_pillar_code = sp_code AND pillar_code = p_code;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_update_locations` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_update_locations`()
BEGIN
	SET @counter = (SELECT COUNT(*) FROM pillars);

	WHILE @counter >= 1 DO
		BEGIN 
			DECLARE pillar_table VARCHAR(250);
			
			IF @counter = 1 THEN 
				SET pillar_table = "livability";
			ELSEIF @counter = 2 THEN 
				SET pillar_table = "transport";
			ELSEIF @counter = 3 THEN 
				SET pillar_table = "energy";
			ELSEIF @counter = 4 THEN 
				SET pillar_table = "buildings";
			ELSEIF @counter = 5 THEN 
				SET pillar_table = "food";
			ELSEIF @counter = 6 THEN 
				SET pillar_table = "waste";
			ELSE SET pillar_table = "water";
			END IF;
			

			INSERT INTO locations
			SELECT TIME_TO_SEC(CURRENT_TIMESTAMP()), street_address, zip_code
			FROM pillar_table;
		END;
		
        SET @counter = @counter - 1;
	END WHILE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-01 20:00:58
