-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.3.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- --------------------------------------------------------

USE `hubproductsmanagement`;

CREATE TABLE IF NOT EXISTS `currency` (
  `id` int NOT NULL,
  `currency` char(3) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;


INSERT INTO `currency` (`id`, `currency`) VALUES
	(0, 'USD'),
	(1, 'EUR');
