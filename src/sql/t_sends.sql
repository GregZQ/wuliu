/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : sends

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2019-01-28 20:07:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_sends
-- ----------------------------
DROP TABLE IF EXISTS `t_sends`;
CREATE TABLE `t_sends` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `carid` varchar(30) DEFAULT NULL,
  `load_time` datetime DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `from_line` varchar(20) DEFAULT NULL,
  `to_line` varchar(20) DEFAULT NULL,
  `save_path` varchar(100) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=336 DEFAULT CHARSET=utf8;
