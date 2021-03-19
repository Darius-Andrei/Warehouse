-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: warehousedb
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.10-MariaDB

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `numeClient` varchar(75) DEFAULT NULL,
  `adresa` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--
-- ORDER BY:  `idClient`

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (2,'Luca George','Bucuresti'),(3,'Sandu Vasile','Cluj-Napoca');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comanda`
--

DROP TABLE IF EXISTS `comanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comanda` (
  `idComanda` int(11) NOT NULL AUTO_INCREMENT,
  `numeClient` varchar(75) DEFAULT NULL,
  `numeProdus` varchar(75) DEFAULT NULL,
  `cantitate` int(11) DEFAULT NULL,
  `pret` double DEFAULT NULL,
  PRIMARY KEY (`idComanda`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comanda`
--
-- ORDER BY:  `idComanda`

LOCK TABLES `comanda` WRITE;
/*!40000 ALTER TABLE `comanda` DISABLE KEYS */;
INSERT INTO `comanda` VALUES (1,'Luca George','apple',5,5),(2,'Luca George','lemon',5,10);
/*!40000 ALTER TABLE `comanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legatura_client_comanda`
--

DROP TABLE IF EXISTS `legatura_client_comanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `legatura_client_comanda` (
  `idComanda` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`idClient`,`idComanda`),
  KEY `idComanda` (`idComanda`),
  CONSTRAINT `legatura_client_comanda_ibfk_1` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`),
  CONSTRAINT `legatura_client_comanda_ibfk_2` FOREIGN KEY (`idComanda`) REFERENCES `comanda` (`idComanda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legatura_client_comanda`
--
-- ORDER BY:  `idClient`,`idComanda`

LOCK TABLES `legatura_client_comanda` WRITE;
/*!40000 ALTER TABLE `legatura_client_comanda` DISABLE KEYS */;
INSERT INTO `legatura_client_comanda` VALUES (1,2),(2,2);
/*!40000 ALTER TABLE `legatura_client_comanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `legatura_produs_comanda`
--

DROP TABLE IF EXISTS `legatura_produs_comanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `legatura_produs_comanda` (
  `idComanda` int(11) NOT NULL,
  `idProdus` int(11) NOT NULL,
  PRIMARY KEY (`idProdus`,`idComanda`),
  KEY `idComanda` (`idComanda`),
  CONSTRAINT `legatura_produs_comanda_ibfk_1` FOREIGN KEY (`idProdus`) REFERENCES `produs` (`idProdus`),
  CONSTRAINT `legatura_produs_comanda_ibfk_2` FOREIGN KEY (`idComanda`) REFERENCES `comanda` (`idComanda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `legatura_produs_comanda`
--
-- ORDER BY:  `idProdus`,`idComanda`

LOCK TABLES `legatura_produs_comanda` WRITE;
/*!40000 ALTER TABLE `legatura_produs_comanda` DISABLE KEYS */;
INSERT INTO `legatura_produs_comanda` VALUES (1,1),(2,4);
/*!40000 ALTER TABLE `legatura_produs_comanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produs`
--

DROP TABLE IF EXISTS `produs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produs` (
  `idProdus` int(11) NOT NULL AUTO_INCREMENT,
  `numeProdus` varchar(75) DEFAULT NULL,
  `cantitate` int(11) DEFAULT NULL,
  `pret` double DEFAULT NULL,
  PRIMARY KEY (`idProdus`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produs`
--
-- ORDER BY:  `idProdus`

LOCK TABLES `produs` WRITE;
/*!40000 ALTER TABLE `produs` DISABLE KEYS */;
INSERT INTO `produs` VALUES (1,'apple',35,1),(3,'orange',40,1.5),(4,'lemon',65,2);
/*!40000 ALTER TABLE `produs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-15  1:42:12
