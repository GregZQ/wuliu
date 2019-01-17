/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : wuliu

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 17/01/2019 12:03:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_invoice
-- ----------------------------
DROP TABLE IF EXISTS `t_invoice`;
CREATE TABLE `t_invoice` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL COMMENT '开票时间',
  `goods_id` int(11) DEFAULT NULL COMMENT '货物id',
  `from_name` varchar(255) DEFAULT NULL COMMENT '发货人名字',
  `from_place` varchar(255) DEFAULT NULL COMMENT '发货人地址',
  `from_phone` varchar(18) DEFAULT NULL COMMENT '发货人手机号',
  `to_name` varchar(255) DEFAULT NULL COMMENT '收货人名字',
  `to_place` varchar(255) DEFAULT NULL COMMENT '收货人地址',
  `to_phone` varchar(18) DEFAULT NULL COMMENT '收货人手机号',
  `goods_name` varchar(255) DEFAULT NULL COMMENT '货物名称',
  `goods_count` varchar(255) DEFAULT NULL COMMENT '货物数量',
  `goods_cube` varchar(255) DEFAULT NULL COMMENT '货物体积',
  `goods_weight` varchar(255) DEFAULT NULL COMMENT '货物重量',
  `goods_unitprice` varchar(255) DEFAULT NULL COMMENT '货物单价',
  `send_type` varchar(255) DEFAULT NULL COMMENT '发货类型',
  `write_back` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '签回单',
  `pay_type` varchar(255) DEFAULT NULL COMMENT '支付方式',
  `pay_count` varchar(255) DEFAULT NULL COMMENT '支付价格',
  `collection` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '代收货款',
  `consignee` varchar(255) DEFAULT NULL COMMENT '收货人',
  `consignee_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '收货人证件号码',
  `sid` int(11) DEFAULT NULL COMMENT '所属列表',
  `sendtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_list
-- ----------------------------
DROP TABLE IF EXISTS `t_list`;
CREATE TABLE `t_list` (
  `sid` int(11) NOT NULL,
  `carid` int(11) DEFAULT NULL COMMENT '卡车id',
  `load_time` datetime DEFAULT NULL COMMENT '装载时间',
  `place` varchar(255) DEFAULT NULL COMMENT '地址',
  `moneys_me` varchar(255) DEFAULT NULL,
  `moneys_he` varchar(255) DEFAULT NULL,
  `putplace` varchar(255) DEFAULT NULL COMMENT '卸货地址',
  `linkphone` varchar(18) DEFAULT NULL COMMENT '联系人电话',
  `totalPages` varchar(255) DEFAULT NULL COMMENT '总页数',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
