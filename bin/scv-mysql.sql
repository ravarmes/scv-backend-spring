-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: scv
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artista`
--

DROP TABLE IF EXISTS `artista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artista`
--

LOCK TABLES `artista` WRITE;
/*!40000 ALTER TABLE `artista` DISABLE KEYS */;
INSERT INTO `artista` VALUES (1,'Jean Claude Van Damme'),(2,'Geoffrey Lewis'),(3,'Bolo Yeung'),(4,'Leonardo DiCaprio'),(5,'Kate Winslet'),(6,'Sam Worthington'),(7,'Zoë Saldaña');
/*!40000 ALTER TABLE `artista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bairro`
--

DROP TABLE IF EXISTS `bairro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bairro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `cidade_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgmx8his7a51210gcsaunulx8b` (`cidade_id`),
  CONSTRAINT `FKgmx8his7a51210gcsaunulx8b` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bairro`
--

LOCK TABLES `bairro` WRITE;
/*!40000 ALTER TABLE `bairro` DISABLE KEYS */;
INSERT INTO `bairro` VALUES (1,'Vila do Sul',1),(2,'Guararema',1),(3,'Maria Ortiz',2),(4,'Centro',2),(5,'Barro Preto',3),(6,'Cidade Jardim',3),(7,'Vale do Sol',4),(8,'Nova Lavras',4);
/*!40000 ALTER TABLE `bairro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cidade`
--

DROP TABLE IF EXISTS `cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `uf_sigla` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9nr1ue1muyycw6uuiq247tkad` (`uf_sigla`),
  CONSTRAINT `FK9nr1ue1muyycw6uuiq247tkad` FOREIGN KEY (`uf_sigla`) REFERENCES `uf` (`sigla`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cidade`
--

LOCK TABLES `cidade` WRITE;
/*!40000 ALTER TABLE `cidade` DISABLE KEYS */;
INSERT INTO `cidade` VALUES (1,'Alegre','ES'),(2,'Cachoeiro de Itapemirim','ES'),(3,'Belo Horizonte','MG'),(4,'Lavras','MG');
/*!40000 ALTER TABLE `cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `debito` double DEFAULT NULL,
  `nascimento` datetime DEFAULT NULL,
  `bairro_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8y9qoaf9mg3nj79rm9unjdcjc` (`bairro_id`),
  CONSTRAINT `FK8y9qoaf9mg3nj79rm9unjdcjc` FOREIGN KEY (`bairro_id`) REFERENCES `bairro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'111.111.111-11','Cliente João',100,'Rua Dr. Brício Mesquita',5,'1980-01-01 03:00:00',3),(2,'111.111.111-11','Cliente José',100,'Rua José Figueiredo',0,'1981-01-01 03:00:00',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucao`
--

DROP TABLE IF EXISTS `devolucao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `devolucao` (
  `data` datetime DEFAULT NULL,
  `emprestimo_id` int(11) NOT NULL,
  `fita_id` int(11) NOT NULL,
  PRIMARY KEY (`emprestimo_id`,`fita_id`),
  CONSTRAINT `FK1q62k656kc28hmsk1bwtufoip` FOREIGN KEY (`emprestimo_id`, `fita_id`) REFERENCES `item_de_emprestimo` (`emprestimo_id`, `fita_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucao`
--

LOCK TABLES `devolucao` WRITE;
/*!40000 ALTER TABLE `devolucao` DISABLE KEYS */;
INSERT INTO `devolucao` VALUES ('2020-04-12 03:00:00',1,1),('2020-04-12 03:00:00',1,2);
/*!40000 ALTER TABLE `devolucao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diretor`
--

DROP TABLE IF EXISTS `diretor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diretor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diretor`
--

LOCK TABLES `diretor` WRITE;
/*!40000 ALTER TABLE `diretor` DISABLE KEYS */;
INSERT INTO `diretor` VALUES (1,'Sheldon Lettich'),(2,'James Cameron'),(3,'Jon Landau'),(4,'Quentin Tarantino');
/*!40000 ALTER TABLE `diretor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprestimo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6n4khp9r51vk81ksiq1cx5bg0` (`cliente_id`),
  CONSTRAINT `FK6n4khp9r51vk81ksiq1cx5bg0` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
INSERT INTO `emprestimo` VALUES (1,'2020-04-10 03:00:00',15,1),(2,'2020-04-13 03:00:00',10,2),(3,'2020-03-02 00:00:00',15,2);
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filme`
--

DROP TABLE IF EXISTS `filme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duracao` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `tipodefilme_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp3im6672gvsf1qvi0hpxl9er8` (`tipodefilme_id`),
  CONSTRAINT `FKp3im6672gvsf1qvi0hpxl9er8` FOREIGN KEY (`tipodefilme_id`) REFERENCES `tipo_de_filme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filme`
--

LOCK TABLES `filme` WRITE;
/*!40000 ALTER TABLE `filme` DISABLE KEYS */;
INSERT INTO `filme` VALUES (1,'2:00','Ação','Duplo Impacto',1),(2,'2:30','Romance','Titanic',2),(3,'3:00','Ficção Científica','Avatar',3),(4,'3:00','Drama','Dor e glória',3);
/*!40000 ALTER TABLE `filme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filme_diretor`
--

DROP TABLE IF EXISTS `filme_diretor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filme_diretor` (
  `filme_id` int(11) NOT NULL,
  `diretor_id` int(11) NOT NULL,
  KEY `FK713eimhe3kmwautqoyj70w3wf` (`diretor_id`),
  KEY `FKa7lun1nkw0rjmixnkx8xivp9l` (`filme_id`),
  CONSTRAINT `FK713eimhe3kmwautqoyj70w3wf` FOREIGN KEY (`diretor_id`) REFERENCES `diretor` (`id`),
  CONSTRAINT `FKa7lun1nkw0rjmixnkx8xivp9l` FOREIGN KEY (`filme_id`) REFERENCES `filme` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filme_diretor`
--

LOCK TABLES `filme_diretor` WRITE;
/*!40000 ALTER TABLE `filme_diretor` DISABLE KEYS */;
INSERT INTO `filme_diretor` VALUES (1,1),(2,2),(3,2),(3,3);
/*!40000 ALTER TABLE `filme_diretor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fita`
--

DROP TABLE IF EXISTS `fita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fita` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `danificada` bit(1) DEFAULT NULL,
  `disponivel` bit(1) DEFAULT NULL,
  `filme_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKatmqv644cf66qlo0vbl8lltms` (`filme_id`),
  CONSTRAINT `FKatmqv644cf66qlo0vbl8lltms` FOREIGN KEY (`filme_id`) REFERENCES `filme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fita`
--

LOCK TABLES `fita` WRITE;
/*!40000 ALTER TABLE `fita` DISABLE KEYS */;
INSERT INTO `fita` VALUES (1,'\0','',1),(2,'\0','',2),(3,'\0','\0',3),(4,'\0','\0',3),(5,'\0','',4);
/*!40000 ALTER TABLE `fita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `bairro_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkrnbsnkj0d02ng8nids477xei` (`bairro_id`),
  CONSTRAINT `FKkrnbsnkj0d02ng8nids477xei` FOREIGN KEY (`bairro_id`) REFERENCES `bairro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (1,'222.222.222-22','Funcionário João',101,'Rua Dr. Brício Mesquita','funcionario1','123',3),(2,'222.222.222-22','Funcionário José',101,'Rua Dr. Brício Mesquita','funcionario2','123',3);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gerente`
--

DROP TABLE IF EXISTS `gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gerente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  `rua` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `bairro_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3jy07thqo2yby5islnin1t4eu` (`bairro_id`),
  CONSTRAINT `FK3jy07thqo2yby5islnin1t4eu` FOREIGN KEY (`bairro_id`) REFERENCES `bairro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gerente`
--

LOCK TABLES `gerente` WRITE;
/*!40000 ALTER TABLE `gerente` DISABLE KEYS */;
INSERT INTO `gerente` VALUES (1,'333.333.333-33','Gerente João',102,'Rua Dr. Brício Mesquita','gerente1','123',3),(2,'333.333.333-33','Gerente José',102,'Rua Dr. Brício Mesquita','gerente2','123',3);
/*!40000 ALTER TABLE `gerente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_de_emprestimo`
--

DROP TABLE IF EXISTS `item_de_emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_de_emprestimo` (
  `entrega` datetime DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `fita_id` int(11) NOT NULL,
  `emprestimo_id` int(11) NOT NULL,
  PRIMARY KEY (`emprestimo_id`,`fita_id`),
  KEY `FK38g1xivrqviga3edmbhp4ao9v` (`fita_id`),
  CONSTRAINT `FK38g1xivrqviga3edmbhp4ao9v` FOREIGN KEY (`fita_id`) REFERENCES `fita` (`id`),
  CONSTRAINT `FKjcpmfohnecuvhd7p5rl43gu1b` FOREIGN KEY (`emprestimo_id`) REFERENCES `emprestimo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_de_emprestimo`
--

LOCK TABLES `item_de_emprestimo` WRITE;
/*!40000 ALTER TABLE `item_de_emprestimo` DISABLE KEYS */;
INSERT INTO `item_de_emprestimo` VALUES ('2020-04-11 03:00:00',5,1,1),('2020-04-12 03:00:00',10,2,1),('2020-04-16 03:00:00',10,3,2),('2020-02-22 00:00:00',15,4,3);
/*!40000 ALTER TABLE `item_de_emprestimo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multa`
--

DROP TABLE IF EXISTS `multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multa` (
  `pago` bit(1) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `emprestimo_id` int(11) NOT NULL,
  `fita_id` int(11) NOT NULL,
  PRIMARY KEY (`emprestimo_id`,`fita_id`),
  CONSTRAINT `FK75ddj1uae4mhu6swlli6jag7h` FOREIGN KEY (`emprestimo_id`, `fita_id`) REFERENCES `item_de_emprestimo` (`emprestimo_id`, `fita_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` VALUES ('\0',5,1,1);
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participacao`
--

DROP TABLE IF EXISTS `participacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `participacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `personagem` varchar(255) DEFAULT NULL,
  `artista_id` int(11) DEFAULT NULL,
  `filme_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3bgg3r2hlfwjq5max7jknomlk` (`artista_id`),
  KEY `FKf6ajr7dx09g7py561v5nyw25` (`filme_id`),
  CONSTRAINT `FK3bgg3r2hlfwjq5max7jknomlk` FOREIGN KEY (`artista_id`) REFERENCES `artista` (`id`),
  CONSTRAINT `FKf6ajr7dx09g7py561v5nyw25` FOREIGN KEY (`filme_id`) REFERENCES `filme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participacao`
--

LOCK TABLES `participacao` WRITE;
/*!40000 ALTER TABLE `participacao` DISABLE KEYS */;
INSERT INTO `participacao` VALUES (1,'Alex',1,1),(2,'Chad',1,1),(3,'Frank',2,1),(4,'Moon',3,1),(5,'Jack',4,2),(6,'Rose',5,2),(7,'Jake Sully',6,3),(8,'Neytiri',7,3);
/*!40000 ALTER TABLE `participacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cliente_id` int(11) DEFAULT NULL,
  `fita_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7cg2jiyn5cf6f6elccvb6963k` (`cliente_id`),
  KEY `FKhuxmihv5apudywbbiiubm6bar` (`fita_id`),
  CONSTRAINT `FK7cg2jiyn5cf6f6elccvb6963k` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKhuxmihv5apudywbbiiubm6bar` FOREIGN KEY (`fita_id`) REFERENCES `fita` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (1,'2020-04-13 03:00:00',0,1,1);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone` (
  `cliente_id` int(11) NOT NULL,
  `telefones` varchar(255) DEFAULT NULL,
  KEY `FK8aafha0njkoyoe3kvrwsy3g8u` (`cliente_id`),
  CONSTRAINT `FK8aafha0njkoyoe3kvrwsy3g8u` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
INSERT INTO `telefone` VALUES (1,'28 1111-2222'),(1,'28 3333-4444'),(2,'28 7777-8888'),(2,'28 5555-6666');
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_de_filme`
--

DROP TABLE IF EXISTS `tipo_de_filme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_de_filme` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `prazo` int(11) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_de_filme`
--

LOCK TABLES `tipo_de_filme` WRITE;
/*!40000 ALTER TABLE `tipo_de_filme` DISABLE KEYS */;
INSERT INTO `tipo_de_filme` VALUES (1,'Promoção A',1,5),(2,'Promoção B',2,10),(3,'Promoção C',3,15);
/*!40000 ALTER TABLE `tipo_de_filme` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uf`
--

DROP TABLE IF EXISTS `uf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uf` (
  `sigla` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sigla`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uf`
--

LOCK TABLES `uf` WRITE;
/*!40000 ALTER TABLE `uf` DISABLE KEYS */;
INSERT INTO `uf` VALUES ('ES','Espírito Santo'),('MG','Minas Gerais');
/*!40000 ALTER TABLE `uf` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-22 16:58:00
