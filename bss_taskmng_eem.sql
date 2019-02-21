CREATE DATABASE  IF NOT EXISTS `bss_taskmng_eem` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `bss_taskmng_eem`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bss_taskmng_eem
-- ------------------------------------------------------
-- Server version	5.5.59

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
-- Table structure for table `tp_board`
--

DROP TABLE IF EXISTS `tp_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_board` (
  `board_id` int(11) NOT NULL AUTO_INCREMENT,
  `board_title` varchar(100) NOT NULL,
  `team_id` int(11) NOT NULL,
  `visibility_status` varchar(45) DEFAULT NULL,
  `status_cd` varchar(45) DEFAULT NULL,
  `reg_id` int(11) DEFAULT NULL,
  `reg_dt` timestamp NULL DEFAULT NULL,
  `chg_id` int(11) DEFAULT NULL,
  `chg_dt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`board_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_board`
--

LOCK TABLES `tp_board` WRITE;
/*!40000 ALTER TABLE `tp_board` DISABLE KEYS */;
INSERT INTO `tp_board` VALUES (1,'Testing Board 1',2,'team','',1,'2019-02-20 03:33:01',NULL,NULL),(2,'Testing Board Name 2',1,'private','',1,'2019-02-20 03:33:16',1,'2019-02-20 07:01:29'),(3,'Testing Board 3',2,'private','',1,'2019-02-20 05:01:38',NULL,NULL);
/*!40000 ALTER TABLE `tp_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_list`
--

DROP TABLE IF EXISTS `tp_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_list` (
  `list_id` int(11) NOT NULL AUTO_INCREMENT,
  `list_title` varchar(100) NOT NULL,
  `board_id` int(11) NOT NULL,
  `status_cd` varchar(45) DEFAULT NULL,
  `reg_id` int(11) DEFAULT NULL,
  `reg_dt` timestamp NULL DEFAULT NULL,
  `chg_id` int(11) DEFAULT NULL,
  `chg_dt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_list`
--

LOCK TABLES `tp_list` WRITE;
/*!40000 ALTER TABLE `tp_list` DISABLE KEYS */;
INSERT INTO `tp_list` VALUES (1,'To Do List',2,'',1,'2019-02-20 03:39:44',NULL,NULL),(2,'Dev Done List',2,'',1,'2019-02-20 03:43:04',NULL,NULL),(3,'Opening List',2,'',1,'2019-02-20 03:43:12',1,'2019-02-20 03:45:51'),(6,'Open List',1,'',1,'2019-02-20 03:49:27',NULL,NULL),(7,'Dev List',1,'',1,'2019-02-20 03:49:35',NULL,NULL),(8,'Dev Done List',1,'',1,'2019-02-20 03:49:47',NULL,NULL);
/*!40000 ALTER TABLE `tp_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_task`
--

DROP TABLE IF EXISTS `tp_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_task` (
  `task_id` int(11) NOT NULL AUTO_INCREMENT,
  `task_title` varchar(100) NOT NULL,
  `task_desc` varchar(100) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `list_id` int(11) NOT NULL,
  `board_id` int(11) NOT NULL,
  `status_cd` varchar(45) DEFAULT NULL,
  `reg_id` int(11) DEFAULT NULL,
  `reg_dt` timestamp NULL DEFAULT NULL,
  `chg_id` int(11) DEFAULT NULL,
  `chg_dt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`task_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_task`
--

LOCK TABLES `tp_task` WRITE;
/*!40000 ALTER TABLE `tp_task` DISABLE KEYS */;
INSERT INTO `tp_task` VALUES (1,'Close Board List API',NULL,NULL,1,2,'',1,'2019-02-20 03:52:09',NULL,NULL),(2,'Delete API for board',NULL,NULL,1,2,'',1,'2019-02-20 03:52:43',NULL,NULL),(3,'Add field reg_id in board',NULL,NULL,8,1,'',1,'2019-02-20 03:56:20',NULL,NULL),(4,'Add field statuscd in list,task table','Add fields','I\'m doing',8,1,'',1,'2019-02-20 03:56:35',1,'2019-02-20 07:09:12'),(9,'add field desc 1',NULL,NULL,2,2,'',1,'2019-02-20 06:52:09',NULL,NULL),(10,'add field desc 2',NULL,NULL,2,2,'',1,'2019-02-20 06:52:14',NULL,NULL),(11,'add field desc 3',NULL,NULL,2,2,'',1,'2019-02-20 06:52:20',NULL,NULL);
/*!40000 ALTER TABLE `tp_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_team`
--

DROP TABLE IF EXISTS `tp_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(45) NOT NULL,
  `team_desc` varchar(100) DEFAULT NULL,
  `team_website` varchar(45) DEFAULT NULL,
  `reg_id` int(11) DEFAULT NULL,
  `reg_dt` timestamp NULL DEFAULT NULL,
  `chg_id` int(11) DEFAULT NULL,
  `chg_dt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`team_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_team`
--

LOCK TABLES `tp_team` WRITE;
/*!40000 ALTER TABLE `tp_team` DISABLE KEYS */;
INSERT INTO `tp_team` VALUES (1,'CRM','CRM Team','www.google.com',1,'2019-02-19 11:59:34',1,'2019-02-19 12:05:17'),(2,'Odoo','Odoo Team',NULL,2,'2019-02-19 11:59:46',NULL,NULL),(5,'Sale','Sale Team',NULL,1,'2019-02-20 14:40:18',NULL,NULL);
/*!40000 ALTER TABLE `tp_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tp_user`
--

DROP TABLE IF EXISTS `tp_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tp_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `reg_dt` timestamp NULL DEFAULT NULL,
  `chg_dt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tp_user`
--

LOCK TABLES `tp_user` WRITE;
/*!40000 ALTER TABLE `tp_user` DISABLE KEYS */;
INSERT INTO `tp_user` VALUES (1,'Ei Ei Mon','eiei@blue-stone.net','123456','2019-02-19 10:21:23','2019-02-19 10:27:13'),(2,'Mon Lay','monlay@blue-stone.net','246801','2019-02-19 10:23:47','2019-02-19 10:25:29'),(3,'EEM Eimon','eimonlay@blue-stone.net','13579','2019-02-20 10:54:54','2019-02-20 10:58:39');
/*!40000 ALTER TABLE `tp_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-21 18:04:34
