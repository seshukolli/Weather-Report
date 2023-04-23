CREATE DATABASE  IF NOT EXISTS `climate_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `climate_db`;
-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: climate_db
-- ------------------------------------------------------
-- Server version	5.7.32-0ubuntu0.18.04.1

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
-- Table structure for table `geo_code`
--

DROP TABLE IF EXISTS `geo_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `geo_code` (
  `gc_id` int(11) NOT NULL AUTO_INCREMENT,
  `gc_lttd` varchar(255) DEFAULT NULL,
  `gc_lngtd` varchar(255) DEFAULT NULL,
  `gc_zp_cd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wthr_rprt`
--

DROP TABLE IF EXISTS `wthr_rprt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wthr_rprt` (
  `wr_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `wr_crtd_on` datetime(6) DEFAULT NULL,
  `wr_hmdty` varchar(255) DEFAULT NULL,
  `wr_mn_dscrptn` varchar(255) DEFAULT NULL,
  `wr_tmprtr` varchar(255) DEFAULT NULL,
  `wr_tmprtr_max` varchar(255) DEFAULT NULL,
  `wr_tmprtr_min` varchar(255) DEFAULT NULL,
  `wr_wnd_spd` varchar(255) DEFAULT NULL,
  `wr_gc` int(11) DEFAULT NULL,
  PRIMARY KEY (`wr_id`),
  KEY `FKo5q4qtvipe6ehcc87v6kt3t75` (`wr_gc`),
  CONSTRAINT `FKo5q4qtvipe6ehcc87v6kt3t75` FOREIGN KEY (`wr_gc`) REFERENCES `geo_code` (`gc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-15  6:19:35