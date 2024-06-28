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
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `user_age` varchar(255) NOT NULL,
  `user_condition1` varchar(255) NOT NULL,
  `user_condition2` varchar(255) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ROLE_ADMIN','ROLE_MEMBER') NOT NULL DEFAULT 'ROLE_MEMBER',
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('30~49','임신 1분기( ~ 12주)','11','0627@naver.com','$2a$10$5PCVFRcbHZ3NfdOLgJ8q0uP409vDubPQNfrVmfGz9RgjJGZ2o.5FC','ROLE_MEMBER','010-2456-6789'),('19~29','임신 1분기(~12주)','11','1234@naver.com','$2a$10$cyk4BZv68da2i5Y/tUeAb.zMjoKmsmHTofMowh1eio2Jgd181uqK2','ROLE_MEMBER',NULL),('30~49','임신 3분기(19주~40주)','15주','44@naver.com','1111','ROLE_MEMBER',NULL),('19~29','임신 1분기( ~ 12주)','12','aaa@naver.com','$2a$10$HaEFR8mvf5GNlNoOS2LvReeeXpeAq5bfgxvmOVO9WJdk90Ft.UnAK','ROLE_MEMBER','010-3333-3333'),('19~29','임신 1분기( ~ 12주)','12','asas@naver.com','$2a$10$eUnK8VzudzUquHRvn.3vheyx6qh6oMiqx6CUPEwyfxXnbsEJxlTxu','ROLE_MEMBER',NULL),('19~29','임신 1분기(~12주)','10주','bcde@naver.com','$2a$10$fXB9RABPBdkHmcIqLwG4wet.7YE9HpvINunQEceeMIUxhhnoXk9Iy','ROLE_MEMBER',NULL),('19~29','임신 2분기(13~18주)','17','pass@naver.com','$2a$10$TmGKODUxja1O2yMdq81Ld.AZ.u4htDi9jZvo.WbQIPrzNviPe5Pj.','ROLE_MEMBER',NULL),('30~49','임신 3분기(19주~40주)','15주','test@naver.com','$2a$10$ZO/j6jc270v/Ri.rwLHhcuE93KWl25FuwtvA3jAEJpAr5jY8QA/9O','ROLE_MEMBER','010-2222-3333'),('19~29','임신 1분기(~12주)','10주','test0625@naver.com','$2a$10$Ai7oEa6plDano73N0nC80.joS5M94a/.QbNbkxuyqHI5198QC6HzS','ROLE_MEMBER','010-5678-1234'),('30~49','임신 2분기(13~18주)','16주','test0626@gmail.com','$2a$10$ZyeGD1gxaYfLg038QM7jP.rLbnBMZvhjlEdfbBPU8izI9ajxlloze','ROLE_MEMBER','010-3456-7890'),('19~29','임신 1분기( ~ 12주)','12','test1@naver.com','$2a$10$47NXvycT5E4Ko0mYYUBn/eVTOjGWoEsMPzbrCon1u.6l6gOqRCL3q','ROLE_MEMBER',NULL),('19~29','임신 1분기( ~ 12주)','12','test2@naver.com','$2a$10$R.7u3uR9E.69AO.cAipW..hzvHzJ3JSdeGRKCM6JbneEfsxc2RdVW','ROLE_MEMBER',NULL),('19~29','임신 1분기( ~ 12주)','12','test3@naver.com','$2a$10$M8qskBQEtUivOilQVnaH.ufduxY/VbKwLsZuJTMG14NFLcVQbZ6Aq','ROLE_MEMBER',NULL),('19~29','임신 1분기( ~ 12주)','12','test4@naver.com','$2a$10$xDejrdBKARqTQlEjef4rVO8Bi38yCQ8DmK9cCM8kDp2di.Iba73XO','ROLE_MEMBER','01012345678'),('19~29','임신 1분기( ~ 12주)','12','test5@naver.com','$2a$10$8CvtvI8FBSBcFmSlxCKeYuP8dLguxF0yzaQUG9bX.EV9dpOv2owEG','ROLE_MEMBER','010-1111-1111'),('19~29','임신 1분기(~12주)','8주','testSave@naver.com','$2a$10$L4lcyP3qJlK868v9pIUaQOoddDcHma6ZHEJlESUBzA/a12Ba1Gko.','ROLE_MEMBER','010-1111-1111'),('30~49','임신 2분기(13~18주)','14주','tst@naver.com','$2a$10$OQvpuEuCclB6poth6reddeuUOnKG7pvbrT/8qVq7P531HDVkqh.86','ROLE_MEMBER',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
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
