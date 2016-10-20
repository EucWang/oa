/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : oa_20160901

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2016-10-20 17:32:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `d_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `d_name` varchar(255) NOT NULL,
  `p_id` bigint(20) NOT NULL,
  `d_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`d_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('2', '研发部', '0', '硬件,平台,前端');
INSERT INTO `department` VALUES ('4', '运维', '2', '运维,DBA,服务器,网络');
INSERT INTO `department` VALUES ('11', '市场部', '0', '开拓市场,迎合用户');
INSERT INTO `department` VALUES ('12', '运营部', '11', '');
INSERT INTO `department` VALUES ('13', '硬件部', '2', '硬件开发,维护');
INSERT INTO `department` VALUES ('14', '产品部', '2', '产品, UI, 市场调研');
INSERT INTO `department` VALUES ('15', '一开发小组', '17', '植物大战僵尸');
INSERT INTO `department` VALUES ('16', '华南市场部', '11', '华南市场部');
INSERT INTO `department` VALUES ('17', '开发组', '2', '开发组');
INSERT INTO `department` VALUES ('18', '二开发小组', '17', '二开发小组');
INSERT INTO `department` VALUES ('19', '三开发小组', '17', '三开发小组');
INSERT INTO `department` VALUES ('21', 'BOSS集合', '0', 'bosses');
INSERT INTO `department` VALUES ('22', 'test group 1', '15', '测试一组');
INSERT INTO `department` VALUES ('23', '产品规划部', '14', '产品规划部');
INSERT INTO `department` VALUES ('25', 'UI部', '14', 'UI部');
INSERT INTO `department` VALUES ('26', 'market research', '14', '市场调研');

-- ----------------------------
-- Table structure for `forum`
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum` (
  `f_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(255) NOT NULL,
  `f_description` varchar(255) DEFAULT NULL,
  `f_position` int(11) DEFAULT '-1',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES ('1', 'JavaSE|JavaEE', 'java开发有关的讨论', '1');
INSERT INTO `forum` VALUES ('3', 'android|ios', '移动端技术有关的讨论', '2');
INSERT INTO `forum` VALUES ('7', 'c|c++|C#|.Net', '传统技术', '4');
INSERT INTO `forum` VALUES ('8', 'PHP', '我大PHP天下无敌', '5');
INSERT INTO `forum` VALUES ('9', 'HTML5|CSS|JS', '前端集中营', '3');

-- ----------------------------
-- Table structure for `privilege`
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `p_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `p_name` varchar(255) NOT NULL,
  `p_description` varchar(255) DEFAULT NULL,
  `p_url` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `is_menu` int(1) DEFAULT '0',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of privilege
-- ----------------------------
INSERT INTO `privilege` VALUES ('1', '0', '系统管理', '岗位,部门,用户,权限管理', null, null, '1');
INSERT INTO `privilege` VALUES ('2', '1', '岗位管理', '岗位管理', '/role/list', null, '2');
INSERT INTO `privilege` VALUES ('3', '1', '部门管理', '部门管理', '/department/list', null, '2');
INSERT INTO `privilege` VALUES ('4', '1', '用户管理', '用户管理', '/user/list', null, '2');
INSERT INTO `privilege` VALUES ('5', '2', '增加岗位', null, '/role/add', null, '0');
INSERT INTO `privilege` VALUES ('6', '2', '删除岗位', null, '/role/del', null, '0');
INSERT INTO `privilege` VALUES ('7', '2', '修改岗位', null, '/role/edit', null, '0');
INSERT INTO `privilege` VALUES ('8', '2', '查询岗位', null, '/role/list', null, '0');
INSERT INTO `privilege` VALUES ('9', '3', '增加部门', null, '/department/add', null, '0');
INSERT INTO `privilege` VALUES ('10', '3', '删除部门', null, '/department/delete', null, '0');
INSERT INTO `privilege` VALUES ('11', '3', '修改部门', null, '/department/edit', null, '0');
INSERT INTO `privilege` VALUES ('12', '3', '查询部门', null, '/department/list', null, '0');
INSERT INTO `privilege` VALUES ('13', '4', '增加用户', null, '/user/add', null, '0');
INSERT INTO `privilege` VALUES ('14', '4', '删除用户', null, '/user/del', null, '0');
INSERT INTO `privilege` VALUES ('15', '4', '修改用户', null, '/user/edit', null, '0');
INSERT INTO `privilege` VALUES ('16', '4', '查询用户', null, '/user/list', null, '0');
INSERT INTO `privilege` VALUES ('17', '0', '审批流转管理', '审批流转管理', null, null, '1');
INSERT INTO `privilege` VALUES ('19', '17', '申请模板管理', '申请模板管理', null, null, '2');
INSERT INTO `privilege` VALUES ('20', '17', '起草申请', '起草申请', null, null, '2');
INSERT INTO `privilege` VALUES ('21', '17', '待我审查', null, null, null, '2');
INSERT INTO `privilege` VALUES ('22', '17', '我的审批查询', null, null, null, '2');
INSERT INTO `privilege` VALUES ('23', '0', '网上交流', null, null, null, '1');
INSERT INTO `privilege` VALUES ('24', '23', '论坛管理', null, '/forum/list', null, '2');
INSERT INTO `privilege` VALUES ('26', '23', '论坛', null, null, null, '2');
INSERT INTO `privilege` VALUES ('27', '2', '修改岗位权限', null, '/role/editPrivilege', null, '0');
INSERT INTO `privilege` VALUES ('28', '24', '版块管理', null, '/forum/list', null, '3');
INSERT INTO `privilege` VALUES ('29', '28', '增加版块', null, '/forum/addUI,/forum/add', null, '4');
INSERT INTO `privilege` VALUES ('30', '28', '修改版块', null, '/forum/editUI,/forum/edit', null, '4');
INSERT INTO `privilege` VALUES ('31', '28', '移动版块', null, '/forum/movePosition', null, '4');
INSERT INTO `privilege` VALUES ('32', '28', '删除版块', null, '/forum/delete', null, '4');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `r_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_name` varchar(255) NOT NULL,
  `r_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'Developer', 'Develop Enginner');
INSERT INTO `role` VALUES ('2', 'QA', 'just test');
INSERT INTO `role` VALUES ('6', 'CTO', '企业内负责技术的最高负责人');
INSERT INTO `role` VALUES ('7', 'PO', '一级产品经理');
INSERT INTO `role` VALUES ('8', 'CEO', 'CEO');
INSERT INTO `role` VALUES ('9', 'ADMIN', '管理员');

-- ----------------------------
-- Table structure for `role_privilege`
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `rp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_id` bigint(20) NOT NULL,
  `p_id` bigint(20) NOT NULL,
  PRIMARY KEY (`rp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_privilege
-- ----------------------------
INSERT INTO `role_privilege` VALUES ('5', '1', '2');
INSERT INTO `role_privilege` VALUES ('7', '1', '1');
INSERT INTO `role_privilege` VALUES ('9', '1', '4');
INSERT INTO `role_privilege` VALUES ('10', '1', '5');
INSERT INTO `role_privilege` VALUES ('11', '1', '6');
INSERT INTO `role_privilege` VALUES ('14', '2', '1');
INSERT INTO `role_privilege` VALUES ('15', '2', '2');
INSERT INTO `role_privilege` VALUES ('16', '2', '3');
INSERT INTO `role_privilege` VALUES ('19', '1', '7');
INSERT INTO `role_privilege` VALUES ('20', '1', '8');
INSERT INTO `role_privilege` VALUES ('25', '1', '13');
INSERT INTO `role_privilege` VALUES ('26', '1', '14');
INSERT INTO `role_privilege` VALUES ('27', '1', '15');
INSERT INTO `role_privilege` VALUES ('28', '1', '16');
INSERT INTO `role_privilege` VALUES ('29', '1', '17');
INSERT INTO `role_privilege` VALUES ('30', '1', '19');
INSERT INTO `role_privilege` VALUES ('31', '1', '20');
INSERT INTO `role_privilege` VALUES ('32', '1', '21');
INSERT INTO `role_privilege` VALUES ('33', '1', '22');
INSERT INTO `role_privilege` VALUES ('36', '1', '3');
INSERT INTO `role_privilege` VALUES ('37', '1', '9');
INSERT INTO `role_privilege` VALUES ('38', '1', '10');
INSERT INTO `role_privilege` VALUES ('39', '1', '11');
INSERT INTO `role_privilege` VALUES ('40', '1', '12');
INSERT INTO `role_privilege` VALUES ('41', '2', '4');
INSERT INTO `role_privilege` VALUES ('42', '2', '13');
INSERT INTO `role_privilege` VALUES ('43', '2', '14');
INSERT INTO `role_privilege` VALUES ('44', '2', '15');
INSERT INTO `role_privilege` VALUES ('45', '2', '16');
INSERT INTO `role_privilege` VALUES ('46', '2', '5');
INSERT INTO `role_privilege` VALUES ('47', '2', '6');
INSERT INTO `role_privilege` VALUES ('48', '2', '7');
INSERT INTO `role_privilege` VALUES ('49', '2', '8');
INSERT INTO `role_privilege` VALUES ('50', '2', '9');
INSERT INTO `role_privilege` VALUES ('51', '2', '10');
INSERT INTO `role_privilege` VALUES ('52', '2', '11');
INSERT INTO `role_privilege` VALUES ('53', '2', '12');
INSERT INTO `role_privilege` VALUES ('54', '2', '17');
INSERT INTO `role_privilege` VALUES ('55', '2', '19');
INSERT INTO `role_privilege` VALUES ('56', '2', '20');
INSERT INTO `role_privilege` VALUES ('57', '2', '21');
INSERT INTO `role_privilege` VALUES ('58', '2', '22');
INSERT INTO `role_privilege` VALUES ('59', '2', '23');
INSERT INTO `role_privilege` VALUES ('60', '2', '24');
INSERT INTO `role_privilege` VALUES ('61', '9', '1');
INSERT INTO `role_privilege` VALUES ('62', '9', '2');
INSERT INTO `role_privilege` VALUES ('63', '9', '5');
INSERT INTO `role_privilege` VALUES ('64', '9', '6');
INSERT INTO `role_privilege` VALUES ('65', '9', '7');
INSERT INTO `role_privilege` VALUES ('66', '9', '8');
INSERT INTO `role_privilege` VALUES ('67', '9', '3');
INSERT INTO `role_privilege` VALUES ('68', '9', '9');
INSERT INTO `role_privilege` VALUES ('69', '9', '10');
INSERT INTO `role_privilege` VALUES ('70', '9', '11');
INSERT INTO `role_privilege` VALUES ('71', '9', '12');
INSERT INTO `role_privilege` VALUES ('73', '9', '13');
INSERT INTO `role_privilege` VALUES ('74', '9', '14');
INSERT INTO `role_privilege` VALUES ('75', '9', '15');
INSERT INTO `role_privilege` VALUES ('76', '9', '16');
INSERT INTO `role_privilege` VALUES ('77', '9', '17');
INSERT INTO `role_privilege` VALUES ('78', '9', '19');
INSERT INTO `role_privilege` VALUES ('79', '9', '20');
INSERT INTO `role_privilege` VALUES ('82', '9', '23');
INSERT INTO `role_privilege` VALUES ('83', '9', '24');
INSERT INTO `role_privilege` VALUES ('85', '9', '27');
INSERT INTO `role_privilege` VALUES ('86', '9', '4');
INSERT INTO `role_privilege` VALUES ('87', '9', '26');
INSERT INTO `role_privilege` VALUES ('88', '9', '21');
INSERT INTO `role_privilege` VALUES ('89', '9', '22');
INSERT INTO `role_privilege` VALUES ('90', '9', '28');
INSERT INTO `role_privilege` VALUES ('91', '9', '29');
INSERT INTO `role_privilege` VALUES ('92', '9', '30');
INSERT INTO `role_privilege` VALUES ('93', '9', '31');
INSERT INTO `role_privilege` VALUES ('94', '9', '32');

-- ----------------------------
-- Table structure for `role_user`
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `ru_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `r_id` bigint(20) NOT NULL,
  `u_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ru_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('7', '3', '7');
INSERT INTO `role_user` VALUES ('8', '1', '7');
INSERT INTO `role_user` VALUES ('9', '2', '7');
INSERT INTO `role_user` VALUES ('13', '3', '8');
INSERT INTO `role_user` VALUES ('14', '2', '8');
INSERT INTO `role_user` VALUES ('15', '7', '16');
INSERT INTO `role_user` VALUES ('16', '9', '15');
INSERT INTO `role_user` VALUES ('17', '1', '1');
INSERT INTO `role_user` VALUES ('21', '6', '10');
INSERT INTO `role_user` VALUES ('22', '8', '10');
INSERT INTO `role_user` VALUES ('23', '0', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `u_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(255) NOT NULL,
  `u_pwd` varchar(255) NOT NULL,
  `u_phoneNum` varchar(255) DEFAULT NULL,
  `u_email` varchar(255) DEFAULT NULL,
  `u_description` varchar(255) DEFAULT NULL,
  `regist_date` date NOT NULL,
  `u_pic` varchar(255) DEFAULT NULL,
  `gender` smallint(6) DEFAULT NULL,
  `nickername` varchar(255) DEFAULT NULL,
  `d_id` bigint(20) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `work_age` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', '123456', '13800880008', 'qqwwdr@126.com', '张二的儿子', '2016-09-21', null, '1', '张三', '2', '1984-02-01 00:00:00', '1', '0');
INSERT INTO `user` VALUES ('2', 'wangdachui', '123456', '13800880009', 'qqwwdr@163.com', '王巨锤的儿子', '2016-09-21', null, '1', '王大锤', '0', '1984-02-01 00:00:00', null, '0');
INSERT INTO `user` VALUES ('7', 'liubei', '123456', null, null, '刘备', '2016-09-22', null, '-1', '刘备', '19', null, null, '0');
INSERT INTO `user` VALUES ('9', 'zhangfei', '123456', null, null, '张飞', '2016-09-22', null, '-1', null, '0', null, null, '0');
INSERT INTO `user` VALUES ('10', 'guanyu', '123456', '13715275022', 'qqwwdr@163.com', '关羽', '2016-09-22', null, '0', '关羽', '0', '1985-06-01 00:00:00', null, '0');
INSERT INTO `user` VALUES ('11', 'zhaoyun', '123456', null, null, '赵云', '2016-09-22', null, '-1', null, '0', null, null, '0');
INSERT INTO `user` VALUES ('13', 'maweiwei', '123456', '13212313212', 'qqwwdr@126.com', 'when i was yong, i listen to the radio, waiting for my favorite song.', '2016-10-09', null, '-1', 'maweiwei', '22', '1984-02-01 00:00:00', null, '0');
INSERT INTO `user` VALUES ('14', 'niuchognchong', '123456', '13212351533', 'qqwwdr@126.com', 'sdfasdf', '2016-10-09', null, '0', 'niufeifei', '18', '1990-11-01 00:00:00', null, '0');
INSERT INTO `user` VALUES ('15', 'admini', '123456', '13812345678', 'qqwwdr@gmail.com', 'admin', '2016-10-13', null, '0', '管理员', '21', '1984-02-01 00:00:00', null, '0');
INSERT INTO `user` VALUES ('16', 'yanyao', '123456', '13715275021', 'qqwwdr@126.com', 'yanyao', '2016-10-14', null, '0', 'yanyao', '26', '1987-05-01 00:00:00', null, '0');
