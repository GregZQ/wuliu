/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : sends

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-28 20:07:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_des
-- ----------------------------
DROP TABLE IF EXISTS `t_des`;
CREATE TABLE `t_des` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `load_place` varchar(255) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
