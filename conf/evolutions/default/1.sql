-- Users schema

-- !Ups

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `telephone` varchar(45) NOT NULL,
  `birthdate` datetime DEFAULT NULL,
  `referred` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- !Downs

DROP TABLE clients;