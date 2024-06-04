-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: moviedb1
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `movie_creators`
--

DROP TABLE IF EXISTS `movie_creators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_creators` (
  `movie_id` bigint NOT NULL,
  `name` varchar(100) NOT NULL,
  `role` enum('actor','director') NOT NULL,
  PRIMARY KEY (`movie_id`,`name`,`role`),
  CONSTRAINT `movie_creators_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_creators`
--

LOCK TABLES `movie_creators` WRITE;
/*!40000 ALTER TABLE `movie_creators` DISABLE KEYS */;
INSERT INTO `movie_creators` VALUES (1,'摩根·弗里曼','actor'),(1,'蒂姆·罗宾斯','actor'),(2,'巩俐','actor'),(2,'张国荣','actor'),(3,'汤姆·汉克斯','actor'),(3,'罗伯特·泽米吉斯','director'),(4,'莱昂纳多·迪卡普里奥','actor'),(4,'詹姆斯·卡梅隆','director'),(5,'渡边彻','actor'),(6,'让·雷诺','actor'),(7,'罗伯托·贝尼尼','actor'),(8,'克里斯托弗·诺兰','director'),(9,'克里斯托弗·诺兰','director'),(9,'莱昂纳多·迪卡普里奥','actor'),(10,'彼得·威尔','director'),(10,'金·凯瑞','actor'),(11,'史蒂文·斯皮尔伯格','director'),(11,'连姆·尼森','actor'),(12,'理查·基尔','actor'),(13,'朱塞佩·托纳多雷','director'),(13,'蒂姆·罗斯','actor'),(14,'拉吉库马尔·希拉尼','director'),(14,'阿米尔·汗','actor'),(15,'克里斯托弗·巴拉蒂','director'),(15,'杰拉尔·朱诺','actor'),(16,'本·贝尔特','director'),(17,'拜恩·霍华德','director'),(17,'金妮弗·古德温','actor'),(18,'刘德华','actor'),(18,'麦兆辉','director'),(19,'查尔斯·劳顿','actor'),(19,'比利·怀尔德','director'),(20,'刘镇伟','director'),(20,'周星驰','actor'),(21,'孔侑','actor'),(21,'黄东赫','director'),(22,'弗朗西斯·福特·科波','director'),(22,'马龙·白兰度','actor'),(23,'奥马·席','actor'),(23,'弗朗索瓦·克鲁塞','actor'),(24,'加布里埃尔·穆奇诺','director'),(24,'威尔·史密斯','actor'),(25,'安东尼·冈萨雷斯','actor'),(25,'李·昂克里奇','director'),(26,'吴大维','actor'),(26,'陈凯歌','director'),(27,'宫崎骏','director'),(27,'秦岚','actor'),(28,'加布里埃莱·穆奇诺','director'),(28,'艾玛·沃森','actor'),(29,'姜武','actor'),(29,'张艺谋','director'),(30,'克里斯托弗·诺兰','director'),(30,'克里斯蒂安·贝尔','actor');
/*!40000 ALTER TABLE `movie_creators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `region` varchar(50) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `is_vip` tinyint(1) DEFAULT '0',
  `weekly_plays` int DEFAULT '0',
  `monthly_plays` int DEFAULT '0',
  `total_plays` int DEFAULT '0',
  `good_reviews` int DEFAULT '0',
  `cover_url` varchar(255) DEFAULT NULL,
  `release_date` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (1,'肖申克的救赎','美国','犯罪',0,14514,58056,3024641,2933901,'img/1.jpg','1994-09-10','2024-06-04 11:25:55','2024-06-04 11:25:55'),(2,'霸王别姬','中国','爱情',0,11451,45804,2235887,2146451,'img/2.jpg','1993-07-26','2024-06-04 11:25:55','2024-06-04 11:25:55'),(3,'阿甘正传','美国','剧情',0,35846,143384,2153607,2140926,'img/3.jpg','1994-06-23','2024-06-04 11:25:55','2024-06-04 11:25:55'),(4,'泰坦尼克号','美国','爱情',0,31415,125660,2292729,2319019,'img/4.jpg','1998-04-03','2024-06-04 11:25:55','2024-06-04 11:25:55'),(5,'千与千寻','日本','奇幻',0,38981,155924,2341637,2201138,'img/5.jpg','2001-07-20','2024-06-04 11:25:55','2024-06-04 11:25:55'),(6,'这个杀手不太冷','法国','犯罪',0,36329,145316,2381147,2238278,'img/6.jpg','1994-09-14','2024-06-04 11:25:55','2024-06-04 11:25:55'),(7,'美丽人生','意大利','战争',0,45316,181264,1379456,1310483,'img/7.jpg','1997-12-20','2024-06-04 11:25:55','2024-06-04 11:25:55'),(8,'星际穿越','美国','科幻',0,51362,205448,1957459,1840011,'img/8.jpg','2014-11-12','2024-06-04 11:25:55','2024-06-04 11:25:55'),(9,'盗梦空间','英国','科幻',0,71568,286272,2156684,2027282,'img/9.jpg','2010-07-16','2024-06-04 11:25:55','2024-06-04 11:25:55'),(10,'楚门的世界','美国','科幻',0,21585,86340,1811869,1703156,'img/10.jpg','1998-06-05','2024-06-04 11:25:55','2024-06-04 11:25:55'),(11,'辛德勒的名单','美国','战争',1,11156,44624,1166010,1107709,'img/11.jpg','1993-11-30','2024-06-04 11:25:55','2024-06-04 11:25:55'),(12,'忠犬八公的故事','英国','剧情',1,32891,131564,1446872,1360059,'img/12.jpg','2009-06-13','2024-06-04 11:25:55','2024-06-04 11:25:55'),(13,'海上钢琴师','意大利','音乐',1,33356,133356,1743596,1345590,'img/13.jpg','1998-10-28','2024-06-04 11:25:55','2024-06-04 11:25:55'),(14,'三傻大闹宝莱坞','印度','喜剧',1,34689,130916,1933750,1837062,'img/14.jpg','2009-12-25','2024-06-04 11:25:55','2024-06-04 11:25:55'),(15,'放牛班的春天','法国','剧情',1,53607,214428,1369254,1300791,'img/15.jpg','2004-03-17','2024-06-04 11:25:55','2024-06-04 11:25:55'),(16,'机器人总动员','美国','科幻',0,32729,32729,1373511,1291100,'img/16.jpg','2008-06-27','2024-06-04 11:25:55','2024-06-04 11:25:55'),(17,'疯狂动物城','美国','喜剧',0,21637,99824,2047437,1904116,'img/17.jpg','2016-03-04','2024-06-04 11:25:55','2024-06-04 11:25:55'),(18,'无间道','中国','犯罪',0,31147,143140,1436107,1364301,'img/18.jpg','2003-09-05','2024-06-04 11:25:55','2024-06-04 11:25:55'),(19,'控方证人','美国','犯罪',1,29456,106736,1140171,1071760,'img/19.jpg','1957-12-17','2024-06-04 11:25:55','2024-06-04 11:25:55'),(20,'大话西游之大圣娶亲','中国','喜剧',1,37459,133424,1594378,1498715,'img/20.jpg','1995-02-04','2024-06-04 11:25:55','2024-06-04 11:25:55'),(21,'熔炉','韩国','剧情',1,35785,257851,1969992,1832092,'img/21.jpg','2011-09-22','2024-06-04 11:25:55','2024-06-04 11:25:55'),(22,'教父','美国','犯罪',1,24956,241956,1015563,934317,'img/22.jpg','1972-03-15','2024-06-04 11:25:55','2024-06-04 11:25:55'),(23,'触不可及','法国','喜剧',1,11451,114514,1178860,2119917,'img/23.jpg','2011-11-02','2024-06-04 11:25:55','2024-06-04 11:25:55'),(24,'当幸福来敲门','美国','剧情',1,26684,266843,1579656,1453283,'img/24.jpg','2006-12-15','2024-06-04 11:25:55','2024-06-04 11:25:55'),(25,'寻梦环游记','美国','喜剧',0,23869,238691,1778425,1689503,'img/25.jpg','2017-11-22','2024-06-04 11:25:55','2024-06-04 11:25:55'),(26,'末代皇帝','中国','剧情',1,12591,52600,936978,880759,'img/26.jpg','1987-10-04','2024-06-04 11:25:55','2024-06-04 11:25:55'),(27,'龙猫','日本','奇幻',0,17468,79905,1319804,1214219,'img/27.jpg','1988-04-16','2024-06-04 11:25:55','2024-06-04 11:25:55'),(28,'怦然心动','美国','爱情',0,10975,54163,1910103,1738193,'img/28.jpg','2010-09-10','2024-06-04 11:25:55','2024-06-04 11:25:55'),(29,'活着','中国','剧情',1,11935,49794,891950,865191,'img/29.jpg','1994-05-17','2024-06-04 11:25:55','2024-06-04 11:25:55'),(30,'蝙蝠侠：黑暗骑士','英国','科幻',0,12941,69514,1110514,1021672,'img/30.jpg','2008-07-14','2024-06-04 11:25:55','2024-06-04 11:25:55');
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `play_records`
--

DROP TABLE IF EXISTS `play_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `play_records` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `movie_id` bigint DEFAULT NULL,
  `play_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `movie_id` (`movie_id`),
  CONSTRAINT `play_records_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `play_records_ibfk_2` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `play_records`
--

LOCK TABLES `play_records` WRITE;
/*!40000 ALTER TABLE `play_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `play_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `is_vip` tinyint(1) DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john_doe','hashed_password','john_doe@example.com',1,'2024-06-04 12:15:00','2024-06-04 12:15:00'),(2,'cqy','123','1225318858@qq.com',1,'2024-06-04 14:31:10','2024-06-04 14:31:10'),(4,'jc','$2a$10$LJgfJ/etcP16Aj9JIn0xZehk/8AqQK7.3nFsoFy3PNOldjLo77ZC2','echo@gmail.com',1,'2024-06-04 14:43:34','2024-06-04 14:43:33');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 22:53:05
