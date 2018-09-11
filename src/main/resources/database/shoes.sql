/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : shoes

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2018-09-11 09:32:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` int(32) NOT NULL AUTO_INCREMENT,
  `adminName` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`adminid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '99e5eafcac5a691429ebc17db8301275');
INSERT INTO `admin` VALUES ('2', 'admi', '99e5eafcac5a691429ebc17db8301275');

-- ----------------------------
-- Table structure for colorpic
-- ----------------------------
DROP TABLE IF EXISTS `colorpic`;
CREATE TABLE `colorpic` (
  `colorpicid` int(11) NOT NULL AUTO_INCREMENT,
  `skuid` int(11) NOT NULL,
  `colorpicaddredd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`colorpicid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of colorpic
-- ----------------------------

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `did` int(11) NOT NULL,
  `dtext` varchar(255) DEFAULT NULL,
  `dstate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------

-- ----------------------------
-- Table structure for orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `detailid` int(11) NOT NULL AUTO_INCREMENT,
  `orderid` int(11) DEFAULT NULL,
  `skuid` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double(32,2) DEFAULT NULL,
  `ticketprice` double(32,2) DEFAULT NULL,
  PRIMARY KEY (`detailid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------

-- ----------------------------
-- Table structure for property
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `propertyid` int(10) NOT NULL AUTO_INCREMENT,
  `propertyname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`propertyid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of property
-- ----------------------------
INSERT INTO `property` VALUES ('1', '颜色');

-- ----------------------------
-- Table structure for propertyvalue
-- ----------------------------
DROP TABLE IF EXISTS `propertyvalue`;
CREATE TABLE `propertyvalue` (
  `propertyvalueid` int(11) NOT NULL AUTO_INCREMENT,
  `propertyvalue` varchar(255) DEFAULT NULL,
  `propertyid` int(11) NOT NULL,
  PRIMARY KEY (`propertyvalueid`),
  KEY `propertyid` (`propertyid`),
  CONSTRAINT `propertyid` FOREIGN KEY (`propertyid`) REFERENCES `property` (`propertyid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of propertyvalue
-- ----------------------------
INSERT INTO `propertyvalue` VALUES ('1', '红色', '1');

-- ----------------------------
-- Table structure for rushbuy
-- ----------------------------
DROP TABLE IF EXISTS `rushbuy`;
CREATE TABLE `rushbuy` (
  `rushbuyid` int(11) NOT NULL AUTO_INCREMENT,
  `shoesid` int(11) DEFAULT NULL,
  `begintime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `rbprice` int(11) DEFAULT NULL,
  `rbamount` int(11) DEFAULT NULL,
  PRIMARY KEY (`rushbuyid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rushbuy
-- ----------------------------

-- ----------------------------
-- Table structure for shoes
-- ----------------------------
DROP TABLE IF EXISTS `shoes`;
CREATE TABLE `shoes` (
  `shoesid` int(11) NOT NULL AUTO_INCREMENT,
  `shoesname` varchar(255) NOT NULL,
  `catalogid` int(11) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `isdropoff` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`shoesid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoes
-- ----------------------------

-- ----------------------------
-- Table structure for shoescatalog
-- ----------------------------
DROP TABLE IF EXISTS `shoescatalog`;
CREATE TABLE `shoescatalog` (
  `catalogid` int(11) NOT NULL AUTO_INCREMENT,
  `catalogname` varchar(255) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  `isleaf` int(11) DEFAULT NULL,
  PRIMARY KEY (`catalogid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoescatalog
-- ----------------------------

-- ----------------------------
-- Table structure for shoesdailysales
-- ----------------------------
DROP TABLE IF EXISTS `shoesdailysales`;
CREATE TABLE `shoesdailysales` (
  `sdid` int(32) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `sales` datetime NOT NULL,
  `shoesid` int(11) NOT NULL,
  PRIMARY KEY (`sdid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoesdailysales
-- ----------------------------

-- ----------------------------
-- Table structure for shoesorder
-- ----------------------------
DROP TABLE IF EXISTS `shoesorder`;
CREATE TABLE `shoesorder` (
  `orderid` int(32) NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) DEFAULT NULL,
  `totalprice` double(32,2) DEFAULT NULL,
  `totticketprice` double(32,2) DEFAULT NULL,
  `buydate` date DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `contactname` varchar(32) DEFAULT NULL,
  `contactphone` varchar(32) DEFAULT NULL,
  `receiptaddress` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `cancel` varchar(32) DEFAULT NULL,
  `validity` int(32) DEFAULT NULL,
  PRIMARY KEY (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoesorder
-- ----------------------------

-- ----------------------------
-- Table structure for shoespic
-- ----------------------------
DROP TABLE IF EXISTS `shoespic`;
CREATE TABLE `shoespic` (
  `picid` int(11) NOT NULL AUTO_INCREMENT,
  `shoesid` int(11) NOT NULL,
  `picaddress` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`picid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoespic
-- ----------------------------

-- ----------------------------
-- Table structure for shoessku
-- ----------------------------
DROP TABLE IF EXISTS `shoessku`;
CREATE TABLE `shoessku` (
  `skuid` int(11) NOT NULL AUTO_INCREMENT,
  `shoesid` int(11) DEFAULT NULL,
  `skuproperty` varchar(255) DEFAULT NULL,
  `price` double(20,2) DEFAULT NULL,
  `ticketprice` double(20,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `skusales` int(11) DEFAULT NULL,
  PRIMARY KEY (`skuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shoessku
-- ----------------------------

-- ----------------------------
-- Table structure for shopcart
-- ----------------------------
DROP TABLE IF EXISTS `shopcart`;
CREATE TABLE `shopcart` (
  `scid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `sctotalprice` double(32,2) DEFAULT NULL,
  `scticketprice` double(32,2) DEFAULT NULL,
  `adddate` date DEFAULT NULL,
  PRIMARY KEY (`scid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopcart
-- ----------------------------

-- ----------------------------
-- Table structure for shopcartdetails
-- ----------------------------
DROP TABLE IF EXISTS `shopcartdetails`;
CREATE TABLE `shopcartdetails` (
  `scdid` int(32) NOT NULL AUTO_INCREMENT,
  `scid` int(11) DEFAULT NULL,
  `skuid` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` double(32,2) DEFAULT NULL,
  `ticketprice` double(32,2) DEFAULT NULL,
  PRIMARY KEY (`scdid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shopcartdetails
-- ----------------------------

-- ----------------------------
-- Table structure for spfilter
-- ----------------------------
DROP TABLE IF EXISTS `spfilter`;
CREATE TABLE `spfilter` (
  `shoesid` int(11) NOT NULL,
  `propertyvalueid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of spfilter
-- ----------------------------

-- ----------------------------
-- Table structure for splink
-- ----------------------------
DROP TABLE IF EXISTS `splink`;
CREATE TABLE `splink` (
  `spid` int(11) NOT NULL AUTO_INCREMENT,
  `shoesid` int(11) NOT NULL,
  `propertyid` int(11) NOT NULL,
  `propertyvalueid` int(11) NOT NULL,
  PRIMARY KEY (`spid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of splink
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` int(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `headpic` varchar(255) DEFAULT NULL,
  `paypassword` varchar(32) DEFAULT NULL,
  `cold` int(32) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'asd0', '456456456', '123123123', null, null, null, null);
INSERT INTO `user` VALUES ('2', 'asd0', '456456456', '123123123', null, null, null, null);
INSERT INTO `user` VALUES ('3', 'asd0', '456456456', '123123123', null, null, null, null);

-- ----------------------------
-- Table structure for useraddress
-- ----------------------------
DROP TABLE IF EXISTS `useraddress`;
CREATE TABLE `useraddress` (
  `userid` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of useraddress
-- ----------------------------
