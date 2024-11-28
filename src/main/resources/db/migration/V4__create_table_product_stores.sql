USE `hubproductsmanagement`;

CREATE TABLE IF NOT EXISTS `product_stores` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `storeId` bigint NOT NULL,
  `productId` bigint NOT NULL,
  `quantity` double NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  `currencyId` int NOT NULL DEFAULT '0',
  `createdBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRODUCT_STORES_STORE_ID` (`storeId`) USING BTREE,
  KEY `FK_PRODUCT_STORES_PRODUCT_ID` (`productId`) USING BTREE,
  KEY `FK_PRODUCT_STORES_CURRENCY_ID` (`currencyId`),
  CONSTRAINT `FK_PRODUCT_STORES_CURRENCY_ID` FOREIGN KEY (`currencyId`) REFERENCES `currency` (`id`),
  CONSTRAINT `FK_PRODUCT_STORES_STORE_ID` FOREIGN KEY (`storeId`) REFERENCES `stores` (`id`),
  CONSTRAINT `FK_PRODUCT_STORES_PRODUCT_ID` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;


INSERT INTO `product_stores` (`id`, `storeId`, `productId`, `quantity`, `price`, `currencyId`) VALUES
	(1, 1, 1, 2, 1, 0),
	(2, 1, 2, 3, 1, 0),
	(3, 2, 3, 7, 1, 0),
	(4, 2, 4, 8, 1, 0);
