/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 10.1.28-MariaDB : Database - poljoprivredna_zadruga
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`poljoprivredna_zadruga` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `poljoprivredna_zadruga`;

/*Table structure for table `manufacturer` */

DROP TABLE IF EXISTS `manufacturer`;

CREATE TABLE `manufacturer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `place` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `manufacturer` */

insert  into `manufacturer`(`id`,`name`,`address`,`place`) values 
(11,'Milos Milanovic','Krcedin 21a','Krcedin'),
(12,'Petar Petrovic','Novi Pazar 11a','Novi Pazar'),
(13,'Ivan Ivanovic','Knez Mihajlova','Beograd');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `category` varchar(15) DEFAULT NULL,
  `measurement_unit` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`description`,`price`,`user_id`,`category`,`measurement_unit`) values 
(12,'jabuka','zlatni delises',156.00,1,'FIRST','KG'),
(13,'kruska','viljamovka',65.00,1,'SECOND','KG'),
(15,'jagode','sumske',125.00,1,'FIRST','KG'),
(24,'jabuka','zlatni delises',120.50,1,'SECOND','KG'),
(26,'kruska','viljamovka',75.00,1,'FIRST','KG'),
(29,'tresnja','japanska',120.00,1,'THIRD','T'),
(30,'visnja','marela',120.00,1,'SECOND','KG'),
(31,'grozdje','hamburg',112.50,1,'FIRST','KG');

/*Table structure for table `redemption_bill` */

DROP TABLE IF EXISTS `redemption_bill`;

CREATE TABLE `redemption_bill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `manufacturer_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `redemption_bill_ibfk_2` (`manufacturer_id`),
  CONSTRAINT `redemption_bill_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `redemption_bill_ibfk_2` FOREIGN KEY (`manufacturer_id`) REFERENCES `manufacturer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `redemption_bill` */

insert  into `redemption_bill`(`id`,`date`,`total`,`user_id`,`manufacturer_id`) values 
(4,'2019-01-17',15000.00,1,11),
(5,'2019-01-11',60125.00,1,12),
(6,'2018-01-11',645.50,1,11),
(11,'2018-01-01',300.00,1,11),
(12,'2012-01-01',371.00,1,11),
(13,'2000-01-01',280.00,1,11),
(14,'2002-01-01',475.00,1,11),
(16,'2017-01-05',75.00,1,11),
(17,'2017-01-05',65.00,1,11),
(18,'2015-01-01',4125.00,1,11),
(19,'2012-01-01',1650.00,1,11),
(20,'2012-01-01',16650.00,1,11),
(21,'1894-01-01',112.50,1,11),
(22,'2001-01-01',28076.50,1,11),
(23,'2011-01-21',0.00,1,11),
(24,'1997-01-22',3430.50,1,11),
(25,'2011-01-01',5115.00,1,11),
(26,'2016-01-11',9802.00,1,12),
(27,'1996-01-31',586.50,1,11),
(30,'2015-01-11',2841.00,1,13);

/*Table structure for table `redemption_bill_item` */

DROP TABLE IF EXISTS `redemption_bill_item`;

CREATE TABLE `redemption_bill_item` (
  `redemption_bill_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `item_no` int(20) unsigned NOT NULL,
  `amount` decimal(10,2) DEFAULT NULL,
  `quantity` decimal(10,2) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`redemption_bill_id`,`item_no`),
  KEY `redemption_bill_item_ibfk_2` (`product_id`),
  CONSTRAINT `redemption_bill_item_ibfk_1` FOREIGN KEY (`redemption_bill_id`) REFERENCES `redemption_bill` (`id`) ON DELETE CASCADE,
  CONSTRAINT `redemption_bill_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

/*Data for the table `redemption_bill_item` */

insert  into `redemption_bill_item`(`redemption_bill_id`,`item_no`,`amount`,`quantity`,`price`,`category`,`product_id`) values 
(4,1,15000.00,100.00,150.00,'FIRST',12),
(5,1,30000.00,200.00,150.00,'FIRST',12),
(5,2,30125.00,250.00,120.50,'SECOND',24),
(6,1,120.50,1.00,120.50,'SECOND',24),
(6,2,525.00,7.00,75.00,'FIRST',26),
(22,1,28076.50,233.00,120.50,'SECOND',24),
(24,1,120.00,1.00,120.00,'FIRST',29),
(24,2,75.00,1.00,75.00,'FIRST',26),
(24,3,120.50,1.00,120.50,'SECOND',24),
(24,4,2750.00,22.00,125.00,'FIRST',15),
(24,5,65.00,1.00,65.00,'SECOND',13),
(24,6,300.00,2.00,150.00,'FIRST',12),
(25,1,2640.00,22.00,120.00,'FIRST',29),
(25,2,2475.00,33.00,75.00,'FIRST',26),
(26,1,3432.00,22.00,156.00,'FIRST',12),
(26,2,6250.00,50.00,125.00,'FIRST',15),
(26,3,120.00,1.00,120.00,'THIRD',29),
(27,1,120.00,1.00,120.00,'THIRD',29),
(27,2,120.50,1.00,120.50,'SECOND',24),
(27,3,125.00,1.00,125.00,'FIRST',15),
(27,4,65.00,1.00,65.00,'SECOND',13),
(27,5,156.00,1.00,156.00,'FIRST',12),
(30,1,125.00,1.00,125.00,'FIRST',15),
(30,2,241.00,2.00,120.50,'SECOND',24),
(30,3,2475.00,33.00,75.00,'FIRST',26);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`firstname`,`lastname`,`username`,`password`,`email`) values 
(1,'Milos','Nikic','milos','milos','milos.nikic@gmail.com'),
(2,'Jovan','Jovanovic','joca','joca','joca@gmail.com'),
(3,'Ivan','Ivanovic','ivan','ivan','ivan@gmail.com'),
(4,'Milutin','Nikic','milutin','milutin','milutinpivkas@gmail.com'),
(5,'a','a','a','a','a'),
(6,'','','','','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
