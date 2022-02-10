-- MySQL dump 10.13  Distrib 8.0.27, for macos11 (arm64)
--
-- Host: 127.0.0.1    Database: therappist
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `patient`
--

CREATE DATABASE IF NOT EXISTS therappist;
USE therappist;

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `username` varchar(55) NOT NULL,
  `email` varchar(55) DEFAULT NULL,
  `issue` varchar(55) NOT NULL,
  `therapist` varchar(55) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(55) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `patient_email_uindex` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` VALUES ('benny','benedetta.tessa@gmail.com','Divorce',NULL,'1999-12-11 10:48:27.000000','Benedetta Tessa','benny'),('pietempesti','pietro.tempesti98@gmail.com','Sleep or Insomnia',NULL,'1998-05-19 11:15:52.000000','Pietro Tempesti','pietro98');

--
-- Table structure for table `therapist`
--

DROP TABLE IF EXISTS `therapist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `therapist` (
  `username` varchar(55) NOT NULL,
  `email` varchar(55) NOT NULL,
  `password` varchar(55) NOT NULL,
  `specialization1` varchar(55) NOT NULL,
  `specialization2` varchar(55) DEFAULT NULL,
  `specialization3` varchar(55) DEFAULT NULL,
  `state` varchar(55) NOT NULL DEFAULT 'pending',
  `biography` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `max_patients` int NOT NULL,
  `accepted_patients` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`username`),
  UNIQUE KEY `therapist_email_uindex` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `therapist`
--

INSERT INTO `therapist` VALUES ('aBechini','alessio.bechini@unipi.it','culo','Trauma','Divorce','Sleep or Insomnia','active','Il mio cuore vi aspetta','2022-02-10 11:02:23.000000','Alessio Bechini','Male',5,0);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-10 13:18:08
