

CREATE TABLE `band` (
  `bandId` int(11) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `anoFormacao` date DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `band` WRITE;
/*!40000 ALTER TABLE `band` DISABLE KEYS */;

INSERT INTO `band` (`bandId`, `nome`, `anoFormacao`)
VALUES
	(1,'Oasis','1991-01-01'),
	(2,'Primal Scream','1982-01-01');

/*!40000 ALTER TABLE `band` ENABLE KEYS */;
UNLOCK TABLES;