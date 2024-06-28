-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: project
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `select_result`
--

DROP TABLE IF EXISTS `select_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `select_result` (
  `rid` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `resultdate` datetime(6) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `age` varchar(255) DEFAULT NULL,
  `condition1` varchar(255) DEFAULT NULL,
  `condition2` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`),
  KEY `Fk_userId_idx` (`user_id`),
  CONSTRAINT `Fk_userId` FOREIGN KEY (`user_id`) REFERENCES `member` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `select_result`
--

LOCK TABLES `select_result` WRITE;
/*!40000 ALTER TABLE `select_result` DISABLE KEYS */;
INSERT INTO `select_result` VALUES (1,'240626 하루','2024-06-26 15:58:51.843075','testSave@naver.com','19~29','preg1','8'),(2,'키워드333','2024-06-26 16:39:28.641828','test3@naver.com','19~29','preg1','12'),(3,'키워드 입력 후 저장','2024-06-26 17:02:19.568047','test3@naver.com','19~29','preg1','12'),(4,'이상없음','2024-06-26 18:00:49.951617','44@naver.com','19~29','preg1','10'),(5,'보여주기','2024-06-26 19:00:56.925068','44@naver.com','19~29','preg1','11'),(6,'zz','2024-06-27 09:58:19.033468','44@naver.com','30~49','preg2','14'),(7,'키워드 22222','2024-06-27 11:26:10.828626','test3@naver.com','19~29','preg1','12'),(8,'국밥','2024-06-27 11:27:13.550342','test3@naver.com','19~29','preg1','12'),(9,'저장 키워드 입력','2024-06-27 11:56:51.917162','test3@naver.com','19~29','preg1','12'),(10,'저장  키워드 입력 111','2024-06-27 15:05:40.320185','test3@naver.com','19~29','preg1','12'),(11,'마지막 테스트','2024-06-28 10:04:42.750152','0627@naver.com','30~49','preg1','11');
/*!40000 ALTER TABLE `select_result` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-28 17:17:14
