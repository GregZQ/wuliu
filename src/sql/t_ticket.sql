/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : sends

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-28 20:07:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_ticket`;
CREATE TABLE `t_ticket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  `goods_id` varchar(50) DEFAULT NULL,
  `from_name` varchar(30) DEFAULT NULL,
  `from_place` varchar(50) DEFAULT NULL,
  `from_phone` varchar(30) DEFAULT NULL,
  `to_name` varchar(30) DEFAULT NULL,
  `to_place` varchar(50) DEFAULT NULL,
  `to_phone` varchar(30) DEFAULT NULL,
  `goods_name` varchar(50) DEFAULT NULL,
  `goods_count` decimal(10,2) DEFAULT NULL,
  `goods_cube` decimal(10,2) DEFAULT NULL,
  `goods_weight` decimal(10,2) DEFAULT NULL,
  `goods_unitprice` decimal(10,2) DEFAULT NULL,
  `send_type` int(10) DEFAULT NULL,
  `write_back` int(10) DEFAULT NULL,
  `pay_type` int(10) DEFAULT NULL,
  `pay_count` decimal(10,2) DEFAULT NULL,
  `collection` decimal(10,2) DEFAULT NULL,
  `consignee` varchar(30) DEFAULT NULL,
  `consignee_phone` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  `uid` int(11) NOT NULL,
  `save_path` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid_reference` (`sid`),
  CONSTRAINT `sid_reference` FOREIGN KEY (`sid`) REFERENCES `t_sends` (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8;
