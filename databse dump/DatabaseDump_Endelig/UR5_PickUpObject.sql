CREATE DATABASE  IF NOT EXISTS `UR5` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `UR5`;
-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: UR5
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `PickUpObject`
--

DROP TABLE IF EXISTS `PickUpObject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PickUpObject` (
  `throwNr` int(11) NOT NULL,
  `radius` int(11) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `shape` varchar(20) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `hitTarget` tinyint(1) DEFAULT NULL,
  `pickTarget` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`throwNr`),
  CONSTRAINT `PickUpObject_ibfk_1` FOREIGN KEY (`throwNr`) REFERENCES `PickUpLoc` (`throwNr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PickUpObject`
--

LOCK TABLES `PickUpObject` WRITE;
/*!40000 ALTER TABLE `PickUpObject` DISABLE KEYS */;
INSERT INTO `PickUpObject` VALUES (6,37,'orange','circle','test',NULL,NULL),(7,38,'orange','circle','test',NULL,NULL),(8,34,'orange','circle','test',NULL,NULL),(9,36,'orange','circle','test',NULL,NULL),(10,39,'orange','circle','test',NULL,NULL),(11,40,'orange','circle','test',NULL,NULL),(12,40,'orange','circle','test',NULL,NULL),(13,41,'orange','circle','test',NULL,NULL),(14,36,'orange','circle','test',NULL,NULL),(15,39,'orange','circle','test',NULL,NULL),(16,40,'orange','circle','test',NULL,NULL),(17,37,'orange','circle','test',NULL,NULL),(18,43,'orange','circle','test',NULL,NULL),(19,33,'orange','circle','test',NULL,NULL),(20,31,'orange','circle','test',NULL,NULL),(21,105,'orange','circle','test',NULL,NULL);
/*!40000 ALTER TABLE `PickUpObject` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-29 12:34:49
