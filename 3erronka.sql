-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: 3erronka
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `balorazioa`
--

DROP TABLE IF EXISTS `balorazioa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balorazioa` (
  `idBalorazioa` int NOT NULL AUTO_INCREMENT,
  `izarKopurua` int NOT NULL,
  `zergatia` longtext,
  `Bezeroa_idBezeroa` int NOT NULL,
  `Barraka_idBarraka` int NOT NULL,
  PRIMARY KEY (`idBalorazioa`),
  KEY `fk_Balorazioa_Bezeroa_idx` (`Bezeroa_idBezeroa`),
  KEY `fk_Balorazioa_Barraka1_idx` (`Barraka_idBarraka`),
  CONSTRAINT `fk_Balorazioa_Barraka1` FOREIGN KEY (`Barraka_idBarraka`) REFERENCES `barraka` (`idBarraka`),
  CONSTRAINT `fk_Balorazioa_Bezeroa` FOREIGN KEY (`Bezeroa_idBezeroa`) REFERENCES `bezeroa` (`idBezeroa`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balorazioa`
--

LOCK TABLES `balorazioa` WRITE;
/*!40000 ALTER TABLE `balorazioa` DISABLE KEYS */;
INSERT INTO `balorazioa` VALUES (11,4,'ggg',2,4);
/*!40000 ALTER TABLE `balorazioa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barraka`
--

DROP TABLE IF EXISTS `barraka`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barraka` (
  `idBarraka` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(45) NOT NULL,
  `modalitatea` enum('Mekanizatua','Uretakoa','Teknologiazkoa','Beldurrezkoa','Ikuskizunezkoa') NOT NULL,
  `kapazitatea` int NOT NULL,
  `prezioa` double NOT NULL,
  `irudia` varchar(100) DEFAULT NULL,
  `Langilea_idLangilea` int NOT NULL,
  PRIMARY KEY (`idBarraka`),
  KEY `fk_Barraka_Langilea1_idx` (`Langilea_idLangilea`),
  CONSTRAINT `fk_Barraka_Langilea1` FOREIGN KEY (`Langilea_idLangilea`) REFERENCES `langilea` (`idLangilea`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barraka`
--

LOCK TABLES `barraka` WRITE;
/*!40000 ALTER TABLE `barraka` DISABLE KEYS */;
INSERT INTO `barraka` VALUES (1,'Mendi Errusiarra','Mekanizatua',50,25,'MendiErrusiarra.jpg',32),(2,'Magia Ikuskizuna','Ikuskizunezkoa',60,30,'MagiaIkuskizuna.jpg',33),(3,'Beldur Etxea','Beldurrezkoa',40,20,'BeldurEtxea.jpg',35),(4,'Simulagailu Futurista','Teknologiazkoa',70,35,'SimulagailuFuturista.jpg',36),(5,'Ibai Bizkorra','Uretakoa',55,27.5,'IbaiBizkorra.jpg',38),(6,'Zurrunbiloa','Mekanizatua',65,32.5,'Zurrunbiloa.jpg',39),(7,'Ilusioen Ikuskizuna','Ikuskizunezkoa',45,22.5,'IlusioIkuskizuna.jpg',41),(8,'Beldurraren Pasabidea','Beldurrezkoa',75,37.5,'BeldurrarenPasabidea.jpg',42),(9,'Errealitate Birtuala 360','Teknologiazkoa',50,25,'ErrealitateBirtuala_360.jpg',44),(10,'Itsaslamina','Uretakoa',60,30,'Itsaslamina.jpg',45),(11,'Talkako Autoak','Mekanizatua',40,20,'TalkakoAutoak.jpg',47),(12,'Zirku Akrobatikoa','Ikuskizunezkoa',70,35,'ZirkuAkrobatikoa.jpg',48),(13,'Beldurraren Laberintoa','Beldurrezkoa',55,27.5,'BeldurrezkoLaberintoa.jpg',50),(14,'Robotak Ekinean','Teknologiazkoa',65,32.5,'RobotakEkinean.jpg',51),(15,'Splash Abentura','Uretakoa',45,22.5,'AbenturaSplash.jpg',53),(16,'Denboraren Makina','Mekanizatua',75,37.5,'DenborarenMakina.jpg',54),(17,'Itzal Antzerkia','Ikuskizunezkoa',50,25,'ItzalAntzerkia.jpg',56),(18,'Ospitale Madarikatua','Beldurrezkoa',60,30,'OspitalMadarikatua.jpg',57),(19,'Holograma Esperientzia','Teknologiazkoa',40,20,'HologramaEsperientzia.jpg',59),(20,'Olatua Handiak','Uretakoa',70,35,'OlatuHandiak.jpg',60),(21,'Zaldiko-maldikoa','Mekanizatua',55,27.5,'ZaldikoMaldiko.jpg',32),(22,'Argien Jaialdia','Ikuskizunezkoa',65,32.5,'ArgienJaialdia.jpg',33),(23,'Gaztelu Sorgindua','Beldurrezkoa',45,22.5,'GazteluSorgindua.jpg',35),(24,'Zona Zibernetikoa','Teknologiazkoa',75,37.5,'ZonaZibernetikoa.jpg',36),(25,'Ibai Basatia','Uretakoa',50,25,'IbaiBasatia.jpg',38),(26,'Mendi Errusiarra Extrema','Mekanizatua',60,30,'MendiErrusiarra_Extrema.jpg',39),(27,'Drone Ikuskizuna','Ikuskizunezkoa',40,20,'DroneIkuskizuna.jpg',41),(28,'Beldurraren Tunela','Beldurrezkoa',70,35,'BeldurrezkoTunela.jpg',42),(29,'Errealitate Nahasia','Teknologiazkoa',55,27.5,'ErrealitateNahasia.jpg',44),(30,'Uharte Urdina','Uretakoa',65,32.5,'UharteUrdina.jpg',45);
/*!40000 ALTER TABLE `barraka` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barrakahistoriko`
--

DROP TABLE IF EXISTS `barrakahistoriko`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barrakahistoriko` (
  `idBarraka` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(45) NOT NULL,
  `modalitatea` enum('Mekanizatua','Uretakoa','Teknologiazkoa','Beldurrezkoa','Ikuskizunezkoa') NOT NULL,
  `kapazitatea` int NOT NULL,
  `prezioa` double NOT NULL,
  `irudia` varchar(100) NOT NULL,
  `egoera` enum('EZABATUTA','EGUNERATUTA') NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`idBarraka`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barrakahistoriko`
--

LOCK TABLES `barrakahistoriko` WRITE;
/*!40000 ALTER TABLE `barrakahistoriko` DISABLE KEYS */;
INSERT INTO `barrakahistoriko` VALUES (1,'Mendi Errusiarraa','Mekanizatua',50,25,'MendiErrusiarra.jpg','EGUNERATUTA','2025-04-08'),(31,'Si','Uretakoa',20,20,'si.jpg','EGUNERATUTA','2025-04-08');
/*!40000 ALTER TABLE `barrakahistoriko` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bezeroa`
--

DROP TABLE IF EXISTS `bezeroa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bezeroa` (
  `idBezeroa` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(45) NOT NULL,
  `abizenak` varchar(100) NOT NULL,
  `erabiltzailea` varchar(11) NOT NULL,
  `pasahitza` varchar(10) NOT NULL,
  `telefonoa` varchar(12) NOT NULL,
  `emaila` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`idBezeroa`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bezeroa`
--

LOCK TABLES `bezeroa` WRITE;
/*!40000 ALTER TABLE `bezeroa` DISABLE KEYS */;
INSERT INTO `bezeroa` VALUES (1,'Ana','Olabarria','ane_ola_bar','ane123','600111222','ane.olabarria@example.com'),(2,'Mikel','Zabala','mik_zab_ala','mikel123','600222333','mikel.zabala@example.com'),(3,'Maite','Gokoetxea','mai_gok_oet','maite123','600333444','maite.gokoetxea@example.com'),(4,'Iker','Mendizabal','ike_men_diz','iker123','600444555','iker.mendizabal@example.com'),(5,'Leire','Agirre','lei_agi_rre','leire123','600555666','leire.agirre@example.com'),(6,'Unai','Bengoetxea','una_ben_get','unai123','600666777','unai.bengoetxea@example.com'),(7,'Miren','Arrieta','mir_arr_iet','miren123','600777888','miren.arrieta@example.com'),(8,'Gorka','Sarasola','gor_sar_aso','gorka123','600888999','gorka.sarasola@example.com'),(9,'Nerea','Egaña','ner_ega_ña','nerea123','600999000','nerea.egaña@example.com'),(10,'Ander','Lasa','and_las_asa','ander123','601000111','ander.lasa@example.com'),(11,'Uxue','Garmenda','uxu_gar_men','uxue123','601111222','uxue.garmenda@example.com'),(12,'Aitor','Zubiri','ait_zub_iri','aitor123','601222333','aitor.zubiri@example.com'),(13,'Itziar','Utkiza','itz_utk_iza','itziar123','601333444','itziar.utkiza@example.com'),(14,'Xabier','Etxeberria','xab_etx_ber','xabier123','601444555','xabier.etxeberria@example.com'),(15,'Garazi','Olaizola','gar_ola_izo','garazi123','601555666','garazi.olaizola@example.com'),(16,'Joseba','Lizarraga','jos_liz_arr','joseba123','601666777','joseba.lizarraga@example.com'),(17,'Maitane','Betia','mai_bet_tia','maitane123','601777888','maitane.betia@example.com'),(18,'Eneko','Odriozola','ene_odr_ozo','eneko123','601888999','eneko.odriozola@example.com'),(19,'Nahia','Alluna','nah_all_una','nahia123','601999000','nahia.alluna@example.com'),(20,'Hodei','Garate','hod_gar_ate','hodei123','602000111','hodei.garate@example.com'),(21,'Ohiane','Mujika','ohi_muj_ika','ohiane12','602111222','ohiane.mujika@example.com'),(22,'Ibai','Urrutia','iba_urr_uti','ibai123','602222333','ibai.urrutia@example.com'),(23,'Ane','Zarate','ane_zar_ate','anezar123','602333444','ane.zarate@example.com'),(24,'Elain','Etxegarai','ela_etx_gar','elain123','602444555','elain.etxegarai@example.com'),(25,'Amaia','Legarreta','ama_leg_ret','amaia123','602555666','amaia.legarreta@example.com'),(26,'Julen','Aranguren','jul_ara_ngu','julen123','602666777','julen.aranguren@example.com'),(27,'Malen','Goenaga','mal_goe_nag','malen123','602777888','malen.goenaga@example.com'),(28,'Eider','Beristain','eid_ber_ist','eider123','602888999','eider.beristain@example.com'),(29,'Beñat','Letxundi','beñ_let_xun','beñat123','602999000','beñat.letxundi@example.com'),(30,'Lorea','Arizmendi','lor_ari_zme','lorea123','603000111','lorea.arizmendi@example.com');
/*!40000 ALTER TABLE `bezeroa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bezerohistoriko`
--

DROP TABLE IF EXISTS `bezerohistoriko`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bezerohistoriko` (
  `idBezeroa` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(45) NOT NULL,
  `abizenak` varchar(100) NOT NULL,
  `erabiltzailea` varchar(11) NOT NULL,
  `pasahitza` varchar(10) NOT NULL,
  `telefonoa` varchar(12) NOT NULL,
  `emaila` varchar(60) DEFAULT NULL,
  `egoera` enum('EZABATUTA','EGUNERATUTA') NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`idBezeroa`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bezerohistoriko`
--

LOCK TABLES `bezerohistoriko` WRITE;
/*!40000 ALTER TABLE `bezerohistoriko` DISABLE KEYS */;
INSERT INTO `bezerohistoriko` VALUES (1,'Ane','Olabarria','ane_ola_bar','ane123','600111222','ane.olabarria@example.com','EGUNERATUTA','2025-04-08'),(53,'Alain','Afonso','ala_afo_red','12345678','000000000','adasdsadsadd@gmail.com','EZABATUTA','2025-04-08'),(54,'AA','AAA','ala_afo_red','AAAAAA','AAAAAAA','AAAA','EZABATUTA','2025-04-08');
/*!40000 ALTER TABLE `bezerohistoriko` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ekintza`
--

DROP TABLE IF EXISTS `ekintza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ekintza` (
  `idEkintza` int NOT NULL AUTO_INCREMENT,
  `izenburua` varchar(100) NOT NULL,
  `informazioa` longtext NOT NULL,
  `data` date NOT NULL,
  `irudia` varchar(100) DEFAULT NULL,
  `Herria_idHerria` int NOT NULL,
  PRIMARY KEY (`idEkintza`),
  KEY `fk_Ekintza_Herria1_idx` (`Herria_idHerria`),
  CONSTRAINT `fk_Ekintza_Herria1` FOREIGN KEY (`Herria_idHerria`) REFERENCES `herria` (`idHerria`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ekintza`
--

LOCK TABLES `ekintza` WRITE;
/*!40000 ALTER TABLE `ekintza` DISABLE KEYS */;
INSERT INTO `ekintza` VALUES (1,'Aste Nagusia Bilbon','Jaialdi handia Bilboko kaleetan.','2025-08-19','AsteNagusia_Bilbo.jpg',6),(2,'Donostiako Jazzaldia','Jazz musika Donostiako antzokietan.','2025-07-25','Jazzaldia_Donostia.jpg',4),(3,'Gasteizko Azoka','Azoka tradizionala Gasteizko plazan.','2025-09-10','Azoka_Gazteiz.jpg',7),(4,'Iruñeko Sanferminak','Entzierro ospetsua Iruñeko kaleetan.','2025-07-07','Sanferminak_Iruña.jpg',5),(5,'Barakaldoko Jaiak','Musika eta dantza Barakaldon.','2025-08-15','Jaiak_Barakaldo.jpg',23);
/*!40000 ALTER TABLE `ekintza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ekintzahistoriko`
--

DROP TABLE IF EXISTS `ekintzahistoriko`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ekintzahistoriko` (
  `idEkintza` int NOT NULL AUTO_INCREMENT,
  `izenburua` varchar(100) NOT NULL,
  `informazioa` longtext NOT NULL,
  `data` date NOT NULL,
  `irudia` varchar(100) DEFAULT NULL,
  `egoera` enum('EZABATUTA','EGUNERATUTA') NOT NULL,
  `data_historiko` varchar(45) NOT NULL,
  PRIMARY KEY (`idEkintza`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ekintzahistoriko`
--

LOCK TABLES `ekintzahistoriko` WRITE;
/*!40000 ALTER TABLE `ekintzahistoriko` DISABLE KEYS */;
INSERT INTO `ekintzahistoriko` VALUES (5,'Barakaldoko Jaia','Musika eta dantza Barakaldon.','2025-08-15','Jaiak_Barakaldo.jpg','EGUNERATUTA','2025-04-08'),(31,'si','ya tu sabe','2020-09-12','jo.jpg','EZABATUTA','2025-04-08');
/*!40000 ALTER TABLE `ekintzahistoriko` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eskaera`
--

DROP TABLE IF EXISTS `eskaera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eskaera` (
  `idEskaera` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(100) NOT NULL,
  `data` date NOT NULL,
  `bezeroa_idBezeroa` int NOT NULL,
  PRIMARY KEY (`idEskaera`),
  KEY `fk_eskaera_bezeroa1_idx` (`bezeroa_idBezeroa`),
  CONSTRAINT `fk_eskaera_bezeroa1` FOREIGN KEY (`bezeroa_idBezeroa`) REFERENCES `bezeroa` (`idBezeroa`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eskaera`
--

LOCK TABLES `eskaera` WRITE;
/*!40000 ALTER TABLE `eskaera` DISABLE KEYS */;
INSERT INTO `eskaera` VALUES (29,'Proba','2025-04-07',1);
/*!40000 ALTER TABLE `eskaera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eskatu`
--

DROP TABLE IF EXISTS `eskatu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eskatu` (
  `idEskatu` int NOT NULL AUTO_INCREMENT,
  `barraka_idBarraka` int NOT NULL,
  `eskaera_idEskaera` int NOT NULL,
  PRIMARY KEY (`idEskatu`),
  KEY `fk_barraka_has_eskaera_eskaera1_idx` (`eskaera_idEskaera`),
  KEY `fk_barraka_has_eskaera_barraka1_idx` (`barraka_idBarraka`),
  CONSTRAINT `fk_barraka_has_eskaera_barraka1` FOREIGN KEY (`barraka_idBarraka`) REFERENCES `barraka` (`idBarraka`),
  CONSTRAINT `fk_barraka_has_eskaera_eskaera1` FOREIGN KEY (`eskaera_idEskaera`) REFERENCES `eskaera` (`idEskaera`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eskatu`
--

LOCK TABLES `eskatu` WRITE;
/*!40000 ALTER TABLE `eskatu` DISABLE KEYS */;
INSERT INTO `eskatu` VALUES (33,6,29),(34,5,29),(35,4,29);
/*!40000 ALTER TABLE `eskatu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `herria`
--

DROP TABLE IF EXISTS `herria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `herria` (
  `idHerria` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(60) NOT NULL,
  `distantzia` float NOT NULL,
  `tarifa` double NOT NULL,
  PRIMARY KEY (`idHerria`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `herria`
--

LOCK TABLES `herria` WRITE;
/*!40000 ALTER TABLE `herria` DISABLE KEYS */;
INSERT INTO `herria` VALUES (1,'Beasain',0,10),(2,'Ordizia',5,10.5),(3,'Tolosa',15,11.5),(4,'Donostia',35,13.5),(5,'Iruña',80,18),(6,'Bilbo',60,16),(7,'Gasteiz',50,15),(8,'Eibar',20,12),(9,'Durango',30,13),(10,'Zarautz',25,12.5),(11,'Hondarribia',40,14),(12,'Azpeitia',10,11),(13,'Bergara',12,11.2),(14,'Oñati',18,11.8),(15,'Arrasate',22,12.2),(16,'Legazpi',25,12.5),(17,'Zumaia',28,12.8),(18,'Deba',30,13),(19,'Mutriku',35,13.5),(20,'Orio',32,13.2),(21,'Azkoitia',8,10.8),(22,'Getxo',65,16.5),(23,'Barakaldo',62,16.2),(24,'Portugalete',63,16.3),(25,'Santurtzi',64,16.4),(26,'Basauri',58,15.8),(27,'Leioa',66,16.6),(28,'Erandio',59,15.9),(29,'Sestao',61,16.1),(30,'Legorreta',7,10.7);
/*!40000 ALTER TABLE `herria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `langilea`
--

DROP TABLE IF EXISTS `langilea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `langilea` (
  `idLangilea` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(45) NOT NULL,
  `abizenak` varchar(100) NOT NULL,
  `erabiltzailea` varchar(11) NOT NULL,
  `pasahitza` varchar(13) NOT NULL,
  `telefonoa` varchar(12) NOT NULL,
  `emaila` varchar(60) DEFAULT NULL,
  `kargua` enum('Admin','Langi') NOT NULL,
  PRIMARY KEY (`idLangilea`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `langilea`
--

LOCK TABLES `langilea` WRITE;
/*!40000 ALTER TABLE `langilea` DISABLE KEYS */;
INSERT INTO `langilea` VALUES (31,'Jon','Etxebarria','jetxebarria','adminpass','600123456','jon@empresa.com','Admin'),(32,'Ana','Olabarria','aolabarria','langipass','600234567','ane@empresa.com','Langi'),(33,'Mikel','Zabala','mzabala','securepass','600345678','mikel@empresa.com','Langi'),(34,'Maite','Goikoetxea','mgoikoetxea','mypassword','600456789','maite@empresa.com','Admin'),(35,'Iker','Mendizabal','imendizabal','trustpass','600567890','iker@empresa.com','Langi'),(36,'Leire','Agirre','lagirre','keypass','600678901','leire@empresa.com','Langi'),(37,'Unai','Bengoetxea','ubengoetxea','lockpass','600789012','unai@empresa.com','Admin'),(38,'Miren','Arrieta','marrieta','secretpass','600890123','miren@empresa.com','Langi'),(39,'Gorka','Sarasola','gsarasola','openpass','600901234','gorka@empresa.com','Langi'),(40,'Nerea','Egaña','negaña','hiddenpass','601012345','nerea@empresa.com','Admin'),(41,'Ander','Lasa','alasa','mypass','601123456','ander@empresa.com','Langi'),(42,'Uxue','Garmendia','ugarmendia','secureword','601234567','uxue@empresa.com','Langi'),(43,'Aitor','Zubiri','azubiri','wordpass','601345678','aitor@empresa.com','Admin'),(44,'Irati','Urkiza','iurkiza','trustword','601456789','irati@empresa.com','Langi'),(45,'Xabier','Etxeberria','xetxeberria','langikey','601567890','xabier@empresa.com','Langi'),(46,'Garazi','Olaizola','golaizola','adminsecure','601678901','garazi@empresa.com','Admin'),(47,'Joseba','Lizarraga','jlizarraga','safeentry','601789012','joseba@empresa.com','Langi'),(48,'Maitane','Beitia','mbeitia','privatepass','601890123','maitane@empresa.com','Langi'),(49,'Eneko','Odriozola','eodriozola','protected','601901234','eneko@empresa.com','Admin'),(50,'Nahia','Altuna','naltuna','entrypass','602012345','nahia@empresa.com','Langi'),(51,'Hodei','Garate','hgarate','mypasskey','602123456','hodei@empresa.com','Langi'),(52,'Oihane','Mujika','omujika','hiddenword','602234567','oihane@empresa.com','Admin'),(53,'Ibai','Urrutia','iurrutia','langisecret','602345678','ibai@empresa.com','Langi'),(54,'Ane','Zarate','azarate','accesspass','602456789','ane.zarate@empresa.com','Langi'),(55,'Ekain','Etxegarai','eetxegarai','adminentry','602567890','ekain@empresa.com','Admin'),(56,'Amaia','Legarreta','alegarreta','protectpass','602678901','amaia@empresa.com','Langi'),(57,'Julen','Aranguren','jaranguren','entrysecure','602789012','julen@empresa.com','Langi'),(58,'Malen','Goenaga','mgoenaga','safeword','602890123','malen@empresa.com','Admin'),(59,'Eider','Beristain','eberistain','privatekey','602901234','eider@empresa.com','Langi'),(60,'Beñat','Lertxundi','blertxundi','langipassword','603012345','benat@empresa.com','Langi');
/*!40000 ALTER TABLE `langilea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `langilehistoriko`
--

DROP TABLE IF EXISTS `langilehistoriko`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `langilehistoriko` (
  `idLangilea` int NOT NULL AUTO_INCREMENT,
  `izena` varchar(45) NOT NULL,
  `abizenak` varchar(100) NOT NULL,
  `erabiltzailea` varchar(11) NOT NULL,
  `pasahitza` varchar(10) NOT NULL,
  `telefonoa` varchar(12) NOT NULL,
  `emaila` varchar(60) DEFAULT NULL,
  `kargua` enum('Admin','Langi') NOT NULL,
  `egoera` enum('EZABATUTA','EGUNERATUTA') NOT NULL,
  `data` date NOT NULL,
  PRIMARY KEY (`idLangilea`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `langilehistoriko`
--

LOCK TABLES `langilehistoriko` WRITE;
/*!40000 ALTER TABLE `langilehistoriko` DISABLE KEYS */;
INSERT INTO `langilehistoriko` VALUES (32,'Ane','Olabarria','aolabarria','langipass','600234567','ane@empresa.com','Langi','EGUNERATUTA','2025-04-08'),(61,'Ander','Araquistain','aaraquist','lanprivate','678098123','ander@empresa.com','Admin','EZABATUTA','2025-04-08');
/*!40000 ALTER TABLE `langilehistoriko` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partehartu`
--

DROP TABLE IF EXISTS `partehartu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partehartu` (
  `idParteHartu` int NOT NULL AUTO_INCREMENT,
  `Barraka_idBarraka` int NOT NULL,
  `Ekintza_idEkintza` int NOT NULL,
  PRIMARY KEY (`idParteHartu`),
  KEY `fk_Barraka_has_Ekintza_Ekintza1_idx` (`Ekintza_idEkintza`),
  KEY `fk_Barraka_has_Ekintza_Barraka1_idx` (`Barraka_idBarraka`),
  CONSTRAINT `fk_Barraka_has_Ekintza_Barraka1` FOREIGN KEY (`Barraka_idBarraka`) REFERENCES `barraka` (`idBarraka`),
  CONSTRAINT `fk_Barraka_has_Ekintza_Ekintza1` FOREIGN KEY (`Ekintza_idEkintza`) REFERENCES `ekintza` (`idEkintza`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partehartu`
--

LOCK TABLES `partehartu` WRITE;
/*!40000 ALTER TABLE `partehartu` DISABLE KEYS */;
/*!40000 ALTER TABLE `partehartu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zalantza`
--

DROP TABLE IF EXISTS `zalantza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zalantza` (
  `idZalantza` int NOT NULL AUTO_INCREMENT,
  `galdera` longtext NOT NULL,
  `erantzuna` longtext,
  `data` date NOT NULL,
  `Bezeroa_idBezeroa` int NOT NULL,
  `Langilea_idLangilea` int DEFAULT '31',
  PRIMARY KEY (`idZalantza`),
  KEY `fk_Zalantza_Bezeroa1_idx` (`Bezeroa_idBezeroa`),
  KEY `fk_Zalantza_Langilea1_idx` (`Langilea_idLangilea`),
  CONSTRAINT `fk_Zalantza_Bezeroa1` FOREIGN KEY (`Bezeroa_idBezeroa`) REFERENCES `bezeroa` (`idBezeroa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zalantza`
--

LOCK TABLES `zalantza` WRITE;
/*!40000 ALTER TABLE `zalantza` DISABLE KEYS */;
/*!40000 ALTER TABLE `zalantza` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-08 12:20:42
