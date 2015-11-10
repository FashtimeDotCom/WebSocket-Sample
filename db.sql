/*
SQLyog Ultimate v11.13 (64 bit)
MySQL - 5.6.26 : Database - yilaidb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`yilaidb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `yilaidb`;

/*Table structure for table `socket_user` */

DROP TABLE IF EXISTS `socket_user`;

CREATE TABLE `socket_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `islogin` int(11) DEFAULT NULL,
  `account` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `photo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `socket_user` */

insert  into `socket_user`(`id`,`islogin`,`account`,`password`,`nickname`,`ip`,`photo`) values (1,1,'abc','1111','1111','0:0:0:0:0:0:0:1','/images/photo/abc_photo.png');
insert  into `socket_user`(`id`,`islogin`,`account`,`password`,`nickname`,`ip`,`photo`) values (2,2,'222','222','222','0:0:0:0:0:0:0:1','/images/photo/222_photo.png');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
