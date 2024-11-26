USE `hubproductsmanagement`;

CREATE TABLE IF NOT EXISTS `stores` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `storeName` varchar(250) NOT NULL,
  `zipCode` varchar(10) NOT NULL,
  `address` varchar(500) NOT NULL,
  `city` varchar(250) NOT NULL,
  `country` varchar(250) NOT NULL,
  `createdBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `updatedBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;


INSERT INTO `stores` (`id`, `storeName`, `zipCode`, `address`, `city`,`country`, `createdBy`, `updatedBy`, `updatedAt`) VALUES
	(1, 'Tribeca paint', '10013', '217 W Broadway', 'New York', 'USA', 'test6', NULL, NULL),
	(2, 'Chinatown Building Supply Inc', '10013', '72 Walker St', 'New York', 'USA', 'test6', NULL, NULL),
	(3, 'Salem St True Value', '021013', '89 Salem St', 'Boston', 'USA', 'test6', NULL, NULL),
	(4, 'Charles Street Supply', '021014', '54 Charles St', 'Boston', 'USA', 'test6', NULL, NULL);