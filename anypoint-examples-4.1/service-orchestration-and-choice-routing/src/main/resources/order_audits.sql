CREATE TABLE `order_audits` (
  `id` varchar(256) NOT NULL,
  `order_id` varchar(256) DEFAULT NULL,
  `total_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
)