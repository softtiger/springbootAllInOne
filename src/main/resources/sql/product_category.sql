/*
Navicat MySQL Data Transfer

Source Server         : 阿里mysql
Source Server Version : 80025
Source Host           : rm-bp1p3t174nt16i896ko.mysql.rds.aliyuncs.com:9876
Source Database       : order

Target Server Type    : MYSQL
Target Server Version : 80025
File Encoding         : 65001

Date: 2022-07-09 16:55:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int NOT NULL,
  `category_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `category_type` smallint NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
