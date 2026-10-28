CREATE TABLE IF NOT EXISTS `person` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `first_name` varchar(80) NOT NULL,
    `address` varchar(160) NOT NULL,
    `last_name` varchar(80) NOT NULL,
    `gender` varchar(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

LOCK TABLES `person` WRITE;

INSERT INTO `person` VALUES
    (1,'Rondonopolis - MT - Brasil','Lucas','Male','Dourado'),
    (2,'Rondonopolis - MT - Brasil','Lucas 2','Male','Dourado'),
    (3,'Rondonopolis - MT - Brasil','Lucas 3','Male','Dourado'),
    (4,'Rondonopolis - MT - Brasil','Lucas 4','Male','Dourado'),
    (5,'Rondonopolis - MT - Brasil','Lucas 5','Male','Dourado');

UNLOCK TABLES;
