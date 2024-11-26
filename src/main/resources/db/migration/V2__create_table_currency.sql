USE `hubproductsmanagement`;

CREATE TABLE IF NOT EXISTS `currency` (
  `id` int NOT NULL,
  `currency` char(3) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;


INSERT INTO `currency` (`id`, `currency`) VALUES
	(0, '$'),
	(1, 'EUR');
