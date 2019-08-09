
CREATE database kiki;
use kiki;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `kiki_userinfo`;
CREATE TABLE `kiki_userinfo` (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uname` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO `kiki_userinfo` VALUES ('1', 'kiki', '1390013336@qq.com');
