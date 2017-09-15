-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.3-m13 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 mambo 的数据库结构
CREATE DATABASE IF NOT EXISTS `mambo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mambo`;

-- 导出  表 mambo.tbl_permission 结构
CREATE TABLE IF NOT EXISTS `tbl_permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(256) DEFAULT '0',
  `RESOURCE_TYPE` enum('menu','button') DEFAULT 'menu',
  `URL` varchar(256) DEFAULT '0',
  `PERMISSION` varchar(256) DEFAULT '0',
  `PARENT_ID` bigint(20) DEFAULT '0',
  `PARENT_IDS` varchar(256) DEFAULT '0',
  `AVAILABLE` int(8) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mambo.tbl_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_permission` ENABLE KEYS */;

-- 导出  表 mambo.tbl_role 结构
CREATE TABLE IF NOT EXISTS `tbl_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(256) NOT NULL DEFAULT '0',
  `DESCRIPTION` varchar(256) NOT NULL DEFAULT '0',
  `AVAILABLE` int(8) NOT NULL DEFAULT '0' COMMENT '是否生效：0不可用  1可用',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mambo.tbl_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_role` ENABLE KEYS */;

-- 导出  表 mambo.tbl_role_permission 结构
CREATE TABLE IF NOT EXISTS `tbl_role_permission` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NOT NULL DEFAULT '0',
  `PERMISSION_ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mambo.tbl_role_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_role_permission` ENABLE KEYS */;

-- 导出  表 mambo.tbl_user 结构
CREATE TABLE IF NOT EXISTS `tbl_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `LOGIN_NAME` varchar(256) DEFAULT NULL COMMENT '登录名',
  `USERNAME` varchar(256) DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(256) DEFAULT NULL COMMENT '密码',
  `COME_YEAR` int(8) DEFAULT '2017' COMMENT '入职年份',
  `SALT` varchar(256) DEFAULT NULL COMMENT '加密密码的盐',
  `STATE` int(8) DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  mambo.tbl_user 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT IGNORE INTO `tbl_user` (`ID`, `LOGIN_NAME`, `USERNAME`, `PASSWORD`, `COME_YEAR`, `SALT`, `STATE`) VALUES
	(1, 'wbao', 'wangbao', 'wangbao', 2010, NULL, NULL);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

-- 导出  表 mambo.tbl_user_role 结构
CREATE TABLE IF NOT EXISTS `tbl_user_role` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_ID` bigint(20) NOT NULL DEFAULT '0',
  `ROLE_ID` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  mambo.tbl_user_role 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `tbl_user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
