CREATE TABLE `orders` (
  `id` varchar(256) NOT NULL,
  `product_id` varchar(256) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `manufacturer` varchar(256) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)