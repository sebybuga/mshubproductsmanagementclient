USE `hubproductsmanagement`;

CREATE TABLE IF NOT EXISTS `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `productName` varchar(250) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  `supplier` varchar(250) CHARACTER SET utf32 COLLATE utf32_bin NOT NULL,
  `status` int NOT NULL,
  `description` LONGTEXT CHARACTER SET utf32 COLLATE utf32_bin,
  `createdBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `updatedBy` varchar(50) CHARACTER SET utf32 COLLATE utf32_bin DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;

INSERT INTO `products` (`id`, `productName`, `supplier`, `description`, `status`, `createdBy`,`createdAt`) VALUES
	(1, 'Minwax Wood Finish', 'MinWax', 'Minwax Wood Finish is a penetrating oil-based wood stain, which provides beautiful rich color that enhances the natural wood grain. It applies easily, absorbs deep into the pores of the wood, and is ideal for staining unfinished wood furniture, cabinets, doors, trim, molding, and hardwood floors.', 0, 'admin', now()),
	(2, 'Minwax Wood Finish Water-Based Solid Color Stain', 'MinWax', 'This penetrating water-based stain opens up a world of possibilities in just one wood-grain-hiding coat. Ideal for small projects, unfinished furniture, cabinets, doors and trim. For interior use only.',0, 'admin', now()),
	(3, 'Repose Gray', 'Sharewin-Williams', 'Tranquil tones and soothing warmth make this light gray a great choice in almost any space. For a complementary trim, pair with Eider White.',0,  'admin', now()),
	(4, 'Dover White', 'Sharewin-Williams', 'A warm, sun-splashed white, this hue makes any room breezy and welcoming. Bring out its color by pairing it with a more saturated shade like Dakota Wheat.',0,  'admin', now());
