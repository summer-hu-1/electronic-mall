/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50645
 Source Host           : localhost:3306
 Source Schema         : onlineshop

 Target Server Type    : MySQL
 Target Server Version : 50645
 File Encoding         : 65001

 Date: 14/03/2022 22:08:37
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`
(
    `id`            int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID ',
    `receivername`  varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '昵称 ',
    `address`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址 ',
    `phonenum`      varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '用户编号 ',
    `receiverphone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '电话号码 ',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address`
VALUES (1, '小苗', '江西省南昌市', '123', '15079044842');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`
(
    `id`         int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID ',
    `goodid`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号 ',
    `goodsname`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称 ',
    `goodremain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片 ',
    `number`     int(11) NULL DEFAULT NULL COMMENT '商品数量 ',
    `price`      double(10, 2
) NULL DEFAULT NULL COMMENT '商品价格',
  `phonenum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart`
VALUES (22, '9', '脉动', '/ img / goods / maidong.png ', 1, 2.80, '123', '2022-03-14 17:44:14', '2022-03-14 17:44:14');
INSERT INTO `cart`
VALUES (25, '12', '和其正', '/ img / goods / heqizheng.png ', 1, 5.00, '123', '2022-03-14 18:09:12',
        '2022-03-14 18:09:12');
INSERT INTO `cart`
VALUES (26, '16', '胡萝卜', '/ img / goods / huluobo.png ', 1, 2.00, '123', '2022-03-14 18:32:41', '2022-03-14 18:32:41');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID ',
    `phonenum` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户账号 ',
    `goodid`   varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号 ',
    `gname`    varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称 ',
    `gremain`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片地址 ',
    `gprice`   double(10, 2
) NULL DEFAULT NULL COMMENT '商品价格',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `collect_id_uindex`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of collect
-- ----------------------------

-- ----------------------------
-- Table structure for discount
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID ',
    `discountid`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '折扣编号 ',
    `discountnum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '折扣 ',
    `goodsid`     varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编号 ',
    `goodsname`   varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称 ',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount`
VALUES (1, '1', '0.7', '9', '脉动');
INSERT INTO `discount`
VALUES (2, '2', '0.8', '10', '汽水');
INSERT INTO `discount`
VALUES (3, '3', '0.9', '11', '沙棘汁');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID ',
    `gid`      varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品ID ',
    `gname`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称 ',
    `gremain`  varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品图片地址 ',
    `gdetails` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品描述 ',
    `gprice`   double(10, 2
) NULL DEFAULT NULL COMMENT '商品单价',
  `types` int(11) NULL DEFAULT NULL COMMENT '商品标签',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods`
VALUES (1, '1', '草莓1', '/ img / goods / goods003.jpg ', '可口草莓1', 4.01, 0, '2021-06-09 21:24:48', '2021-06-09 21:24:50');
INSERT INTO `goods`
VALUES (2, '2', '苹果', '/ img / 2.jpg', '红富士苹果', 2.00, 0, '2021-06-09 21:25:24', '2021-06-09 21:24:48');
INSERT INTO `goods`
VALUES (3, '3', '橙子', '/ img / goods / goods001.jpg ', '金黄的大橙子', 4.00, 0, '2021-06-09 21:25:24', '2021-06-09 21:25:16');
INSERT INTO `goods`
VALUES (4, '4', '葡萄', '/ img / goods / goods002.jpg ', '吐鲁番葡萄', 12.00, 0, '2021-06-09 21:25:24',
        '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (5, '5', '大虾', '/ img / goods / goods018.jpg ', '山东大虾', 5.00, 2, '2021-06-09 21:25:24', '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (6, '6', '带鱼', '/ img / goods / goods020.jpg ', '好吃的带鱼', 50.00, 2, '2021-06-09 21:25:24',
        '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (7, '7', '扇贝', '/ img / goods / goods019.jpg ', '大神快来快来', 20.00, 2, '2021-06-09 21:25:24',
        '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (8, '8', '皮皮虾', '/ img / goods / goods021.jpg ', '啥的客户', 10.00, 2, '2021-06-09 21:25:24',
        '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (9, '9', '脉动', '/ img / goods / maidong.png ', '不在状态，脉动一下', 4.00, 1, '2021-06-09 21:25:24', '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (10, '10', '汽水', '/ img / goods / qishui.png ', '栓双', 8.00, 1, '2021-06-09 21:25:24', '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (11, '11', '沙棘汁', '/ img / goods / shajizhi.png ', '好喝', 3.00, 1, '2021-06-09 21:25:24',
        '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (12, '12', '和其正', '/ img / goods / heqizheng.png ', 'dsa asd', 5.00, 1, '2021-06-09 21:25:24',
        '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (13, '13', '菠菜', '/ img / goods / bocai.png ', '大', 2.00, 3, '2021-06-09 21:25:24', '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (14, '14', '生菜', '/ img / goods / shengcai.png ', 'asdajlskdja', 2.00, 3, '2021-06-09 21:25:24',
        '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (15, '15', '番茄', '/ img / goods / fanqie.png ', '好吃', 1.00, 3, '2021-06-09 21:25:24', '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (16, '16', '胡萝卜', '/ img / goods / huluobo.png ', 'asdkjlajkld', 2.00, 3, '2021-06-09 21:25:24', '2021-06-09 21:25:24');
INSERT INTO `goods`
VALUES (17, '17', '冰红茶', '/ img / goods / huluobo.png ', '解渴', 4.50, 4, '2021-06-16 18:13:14', '2021-06-16 18:13:14');
INSERT INTO `goods`
VALUES (18, '18', '冰红茶', '/ img / goods / huluobo.png ', '解渴', 4.50, 4, '2022-03-13 21:01:50', '2022-03-13 21:01:50');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID ',
    `username`    varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名 ',
    `phonenum`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名 ',
    `password`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码 ',
    `nationality` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '地址 ',
    `category`    varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色 ',
    `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user`
VALUES (1, '张一', '110', '110', '中国', '1', '2021-06-09 21:19:20', '2021-06-09 21:19:20');
INSERT INTO `sys_user`
VALUES (2, '张二', '1101', '1101', '中国', '2', '2021-06-09 21:19:41', '2021-06-09 21:19:41');
INSERT INTO `sys_user`
VALUES (3, '张三', '1102', '1102', '中国', '2', '2021-06-09 21:20:00', '2021-06-09 21:20:00');
INSERT INTO `sys_user`
VALUES (4, '123', '123', '1234', '中国', '2', '2021-06-15 21:27:45', '2021-06-15 21:27:45');

-- ----------------------------
-- Table structure for userorder
-- ----------------------------
DROP TABLE IF EXISTS `userorder`;
CREATE TABLE `userorder`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID ',
    `orderid`   varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号 ',
    `goodsname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单名称 ',
    `number`    int(11) NULL DEFAULT NULL COMMENT '商品数量 ',
    `price`     double(10, 2
) NULL DEFAULT NULL COMMENT '商品价格',
  `time` datetime NULL DEFAULT NULL COMMENT '时间',
  `phonenum` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户账号',
  `receivername` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `receiverphone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of userorder
-- ----------------------------
INSERT INTO `userorder`
VALUES (19, '988432', '冰红茶', 1, 4.50, '2022-03-09 19:05:56', '123', '小苗', '15079044842', '江西南昌');
INSERT INTO `userorder`
VALUES (21, '202203142206567EF311FA4E274F488009F04E9287FF68', '脉动', 1, 2.80, '2022-03-14 22:06:56', '123', '小苗', '15079044842', '江西省南昌市');

SET
FOREIGN_KEY_CHECKS = 1;
