/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : sends

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-28 20:07:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_place
-- ----------------------------
DROP TABLE IF EXISTS `t_place`;
CREATE TABLE `t_place` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `place` varchar(50) DEFAULT NULL,
  `uid` tinyint(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
