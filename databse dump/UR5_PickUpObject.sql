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
  `colour` varchar(20) DEFAULT NULL,
  `shape` varchar(20) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`throwNr`),
  CONSTRAINT `PickUpObject_ibfk_1` FOREIGN KEY (`throwNr`) REFERENCES `PickUpLoc` (`ThrowNr`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PickUpObject`
--

LOCK TABLES `PickUpObject` WRITE;
/*!40000 ALTER TABLE `PickUpObject` DISABLE KEYS */;
INSERT INTO `PickUpObject` VALUES (14,300,'Blue','Circle','Billeder/pic1.jpgs'),(15,300,'Blue','Circle','Billeder/pic1.jpg'),(16,300,'Blue','Circle','Billeder/pic1.jpg'),(17,300,'Blue','Circle','Billeder/pic1.jpg'),(18,300,'Blue','Circle','Billeder/pic1.jpg'),(19,310,'Blue','Circle','Billeder/pic1.jpg'),(20,310,'Blue','Circle','Billeder/pic1.jpg'),(21,310,'Blue','Circle','Billeder/pic1.jpg'),(22,310,'Blue','Circle','Billeder/pic1.jpg'),(23,310,'Blue','Circle','Billeder/pic1.jpg'),(24,310,'Blue','Circle','Billeder/pic1.jpg'),(25,310,'Blue','Circle','Billeder/pic1.jpg'),(26,310,'Blue','Circle','Billeder/pic1.jpg'),(27,310,'Blue','Circle','Billeder/pic1.jpg'),(28,310,'Blue','Circle','Billeder/pic1.jpg'),(29,310,'Blue','Circle','Billeder/pic1.jpg'),(30,310,'Blue','Circle','Billeder/pic1.jpg'),(31,310,'Blue','Circle','Billeder/pic1.jpg'),(32,310,'Blue','Circle','Billeder/pic1.jpg'),(33,310,'Blue','Circle','Billeder/pic1.jpg'),(34,310,'Blue','Circle','Billeder/pic1.jpg'),(35,300,'206cdf','Circle','D:/Billeder/throwX.PNG'),(36,300,'206cdf','Circle','D:/Billeder/throwX.PNG'),(37,300,'206cdf','Circle','D:/Billeder/throwX.PNG'),(38,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(39,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(40,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(41,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(42,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(43,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(44,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(45,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(46,300,'206cdf','Square','D:/Billeder/throwX.PNG'),(47,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(48,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(49,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(50,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(51,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(52,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(53,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(54,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(55,300,'206cdf','Sphere','D:/Billeder/throwX.PNG'),(56,300,'206cdf','Sphere','D:/Billeder/throwX.PNG');
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

-- Dump completed on 2018-11-22  9:00:18
