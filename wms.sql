/*
 Navicat Premium Dump SQL

 Source Server         : Test01
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 09/08/2025 21:14:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `parent_id` int NOT NULL COMMENT '父类ID',
  `create_by` int NOT NULL DEFAULT 1 COMMENT '创建人',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (2, '男装', 0, 1, '2025-07-18 16:31:51', '2025-07-28 21:09:34');
INSERT INTO `category` VALUES (3, '女装', 0, 1, '2025-07-17 16:31:44', '2025-07-28 21:09:31');
INSERT INTO `category` VALUES (4, '童装', 0, 1, '2025-07-25 16:31:46', '2025-07-28 21:09:37');
INSERT INTO `category` VALUES (5, '男装上衣', 2, 1, '2025-06-26 16:31:53', '2025-07-28 21:09:43');
INSERT INTO `category` VALUES (6, '男装裤子', 2, 1, '2025-07-26 16:31:57', '2025-07-28 21:09:40');
INSERT INTO `category` VALUES (7, '女装上衣', 3, 1, '2025-07-27 16:32:00', '2025-07-28 21:09:45');
INSERT INTO `category` VALUES (8, '女装裤子', 3, 1, '2025-07-27 16:32:05', '2025-07-28 21:09:47');
INSERT INTO `category` VALUES (9, '女装鞋子', 3, 1, '2025-07-27 16:32:03', '2025-07-28 21:09:50');
INSERT INTO `category` VALUES (10, '童装上衣', 4, 1, '2025-07-27 16:32:07', '2025-07-28 21:09:52');
INSERT INTO `category` VALUES (11, '男装卫衣', 5, 1, '2025-07-27 16:32:11', '2025-07-28 23:43:06');
INSERT INTO `category` VALUES (12, '男装棉袄', 5, 1, '2025-07-27 16:32:13', '2025-07-28 23:43:47');
INSERT INTO `category` VALUES (13, '男装鞋子', 6, 1, '2025-07-27 16:32:17', '2025-07-28 21:09:59');
INSERT INTO `category` VALUES (14, '女装卫衣', 7, 1, '2025-07-27 16:32:15', '2025-07-28 21:10:01');
INSERT INTO `category` VALUES (15, '男装内裤', 6, 1, '2025-07-28 22:55:10', '2025-07-28 22:55:10');
INSERT INTO `category` VALUES (16, '男士袜子', 13, 1, '2025-07-28 22:57:49', '2025-07-28 22:57:49');
INSERT INTO `category` VALUES (20, '童装裤子', 4, 1, '2025-07-29 09:03:50', '2025-07-29 09:03:50');
INSERT INTO `category` VALUES (21, '童装鞋子', 20, 1, '2025-07-29 09:04:20', '2025-07-29 09:04:20');

-- ----------------------------
-- Table structure for pcd
-- ----------------------------
DROP TABLE IF EXISTS `pcd`;
CREATE TABLE `pcd`  (
  `id` int NOT NULL COMMENT '主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省市区名字',
  `level` int NULL DEFAULT NULL COMMENT '区域级别  1 省  2  市  3  区',
  `parent_id` int NULL DEFAULT NULL COMMENT '父ID，0 表示省',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '省市区表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pcd
-- ----------------------------
INSERT INTO `pcd` VALUES (1, '湖北省', 1, 0, 1, '2025-08-03 21:16:45', NULL, NULL, NULL, 1);
INSERT INTO `pcd` VALUES (2, '十堰市', 2, 1, 1, '2025-08-03 21:17:44', NULL, NULL, NULL, 1);
INSERT INTO `pcd` VALUES (3, '郧阳区', 3, 2, 1, '2025-08-03 21:18:18', NULL, NULL, NULL, 1);
INSERT INTO `pcd` VALUES (110000, '北京市', 1, 0, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (110100, '北京市', 2, 110000, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (110101, '东城区', 3, 110100, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (110102, '西城区', 3, 110100, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (110105, '朝阳区', 3, 110100, 3, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (310000, '上海市', 1, 0, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (310100, '上海市', 2, 310000, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (310101, '黄浦区', 3, 310100, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (310105, '静安区', 3, 310100, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (330000, '浙江省', 1, 0, 4, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (330100, '杭州市', 2, 330000, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (330102, '上城区', 3, 330100, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (330105, '拱墅区', 3, 330100, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (440000, '广东省', 1, 0, 3, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (440100, '广州市', 2, 440000, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (440103, '荔湾区', 3, 440100, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (440104, '越秀区', 3, 440100, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (440300, '深圳市', 2, 440000, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (440301, '罗湖区', 3, 440300, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (440303, '南山区', 3, 440300, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (510000, '四川省', 1, 0, 5, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (510100, '成都市', 2, 510000, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (510104, '锦江区', 3, 510100, 1, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);
INSERT INTO `pcd` VALUES (510105, '青羊区', 3, 510100, 2, '2025-08-03 21:26:41', 'admin', NULL, NULL, 1);

-- ----------------------------
-- Table structure for sku
-- ----------------------------
DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `real_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '真实价格',
  `spu_id` bigint NOT NULL COMMENT 'Spu关联ID',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品条码',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品标题',
  `unit` int NULL DEFAULT NULL COMMENT '商品单位',
  `product_weight` int NULL DEFAULT NULL COMMENT '商品重量',
  `product_length` int NULL DEFAULT NULL COMMENT '商品长度',
  `product_width` int NULL DEFAULT NULL COMMENT '商品宽度',
  `product_height` int NULL DEFAULT NULL COMMENT '商品高度',
  `product_volume` int NULL DEFAULT NULL COMMENT '商品体积',
  `spec` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '其他规格',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品SKU表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (1, 299.00, 399.00, 10, 'cxk123456', '蔡徐坤的卫衣', 1, 6, 40, 50, 20, 40000, '0', '11', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (2, 288.00, 366.00, 10, 'cxk123457', '蔡徐坤的卫衣', 1, 6, 40, 50, 20, 40000, '0', '12', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (3, 998.00, 366.00, 11, 'cxk123457', '蔡徐坤的裙子', 1, 6, 40, 50, 20, 40000, '0', '13', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (4, 788.00, 366.00, 11, 'cxk123457', '蔡徐坤的裙子', 1, 6, 40, 50, 20, 50000, '0', '14', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (5, 3.00, 3.00, 12, 'wazi123', '精品袜子', 3, 3, 3, 3, 3, 27, '3', '3', '2025-08-09 08:38:56', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (6, 3.00, 3.00, 12, 'wazi124', '精品袜子', 3, 3, 3, 3, 3, 27, '3', '3', '2025-08-09 08:38:56', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for spu
-- ----------------------------
DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品标题',
  `sub_title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '副标题',
  `category_id1` int NULL DEFAULT NULL COMMENT '分类ID1',
  `category_id2` int NULL DEFAULT NULL COMMENT '分类ID2',
  `category_id3` int NULL DEFAULT NULL COMMENT '分类ID3',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商品描述',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '商品详情',
  `brand_id` int NULL DEFAULT NULL COMMENT '品牌ID',
  `supplier_id` int NULL DEFAULT NULL COMMENT '供应商ID',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品Spu表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES (10, '蔡徐坤的卫衣', '中性', 14, 11, NULL, '男女通用', '<p>222</p>', 999, 5, '', '2025-08-08 17:13:17', NULL, '2025-08-08 17:13:17', NULL, 1);
INSERT INTO `spu` VALUES (11, '蔡徐坤的裙子', 'cxk', 7, NULL, NULL, '裙子', '<p>222</p>', 999, 1, '', '2025-08-08 17:13:17', NULL, '2025-08-08 17:13:17', NULL, 1);
INSERT INTO `spu` VALUES (12, '精品袜子', '小短袜', 13, 9, NULL, 'qqqqqqqqqqqqqqqq', '<p>ayifgcbiysVSIdgvfcayuwegfaifawegwaewgfqeqefqe</p>', 2, 7, NULL, '2025-08-09 08:38:56', NULL, '2025-08-09 08:38:56', NULL, 1);
INSERT INTO `spu` VALUES (13, '风衣', 'qq', 11, 12, 21, '11', '<p>qqq</p>', 1, 3, NULL, '2025-08-09 08:45:07', NULL, '2025-08-09 08:45:07', NULL, 1);

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `id` int NOT NULL COMMENT '主键',
  `supplier_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '供应商名字',
  `link_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人名字',
  `link_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系人手机号',
  `city_code` int NULL DEFAULT NULL COMMENT '城市ID',
  `province_id` int NULL DEFAULT NULL COMMENT '省份ID',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '渠道商表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (1, '北京华联供应商', '张经理', '13800138001', 110105, 110000, '北京市朝阳区建国路123号', 'saas001', '2025-08-03 21:54:14', 'admin', NULL, NULL, 1);
INSERT INTO `supplier` VALUES (2, '上海供应链管理有限公司', '李主管', '13800138002', 310101, 310000, '上海市黄埔区陆家嘴路456号', 'saas002', '2025-08-03 21:54:14', 'admin', NULL, NULL, 1);
INSERT INTO `supplier` VALUES (3, '湖北十堰供应商', '杨经理', '13875489547', 3, 1, '湖北十堰郧阳区汉江桥头', NULL, '2025-08-04 01:12:04', NULL, NULL, NULL, 1);
INSERT INTO `supplier` VALUES (4, '湖北十堰供应商', '蔡经理', '13875548454', 3, 1, '湖北十堰郧阳区汉江桥头', NULL, '2025-08-04 01:12:04', NULL, NULL, NULL, 1);
INSERT INTO `supplier` VALUES (5, '上海供米哈游有限公司', '龙主管', '13800178002', 310101, 310000, '上海市黄埔区陆家嘴路333号', 'saas002', '2025-08-03 21:54:14', 'admin', NULL, NULL, 1);
INSERT INTO `supplier` VALUES (6, '上海供应链管理有限公司', '李主管', '13800138002', 310101, 310000, '上海市黄埔区陆家嘴路456号', 'saas002', '2025-08-03 21:54:14', 'admin', NULL, NULL, 1);
INSERT INTO `supplier` VALUES (7, '北京京✌供应商', '坤经理', '13800178001', 110105, 110000, '北京市朝阳区建国路666号', 'saas001', '2025-08-03 21:54:14', 'admin', NULL, NULL, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `employee_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工编号',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '员工名称',
  `role` int NULL DEFAULT NULL COMMENT '角色',
  `gender` int NOT NULL COMMENT '性别',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` int NOT NULL COMMENT '状态',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账户',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1234' COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '715', '胡桃', NULL, 1, '17878788787', 1, 'hutao', '1234');
INSERT INTO `user` VALUES (2, '388', '曾致远', NULL, 1, '19546996445', 1, 'Zhiyuan', '1234');
INSERT INTO `user` VALUES (3, '799', '江秀英', NULL, 1, '15149825033', 1, 'Xiuying', '1234');
INSERT INTO `user` VALUES (4, '334', '龙詩涵', NULL, 1, '204064369', 0, 'Shihan', '1234');
INSERT INTO `user` VALUES (5, '396', '谭嘉伦', NULL, 2, '7555224278', 1, 'Jialun', '1234');
INSERT INTO `user` VALUES (6, '869', '蔡安琪', NULL, 1, '17981676299', 0, 'Anqi', '1234');
INSERT INTO `user` VALUES (7, '323', '陆秀英', NULL, 2, '1054811793', 0, 'Xiuying', '1234');
INSERT INTO `user` VALUES (9, '883', '李璐', NULL, 2, '17033280815', 1, 'Lu', '1234');
INSERT INTO `user` VALUES (10, '342', '宋睿', NULL, 1, '76923047250', 0, 'Rui', '1234');
INSERT INTO `user` VALUES (11, '208', '孔岚', NULL, 2, '14410628539', 0, 'Lan', '1234');
INSERT INTO `user` VALUES (12, '271', '薛宇宁', NULL, 2, '1087935696', 0, 'Yuning', '1234');
INSERT INTO `user` VALUES (13, '992', '吕震南', NULL, 2, '7607751347', 1, 'Zhennan', '1234');
INSERT INTO `user` VALUES (14, '169', '周云熙', NULL, 1, '101430897', 0, 'Yunxi', '1234');
INSERT INTO `user` VALUES (15, '824', '黎杰宏', NULL, 1, '207104974', 1, 'Jiehong', '1234');
INSERT INTO `user` VALUES (16, '968', '贺睿', NULL, 1, '7603163503', 1, 'Rui', '1234');
INSERT INTO `user` VALUES (17, '611', '蒋震南', NULL, 2, '204342198', 1, 'Zhennan', '1234');
INSERT INTO `user` VALUES (18, '603', '李致远', NULL, 1, '76975952740', 1, 'Zhiyuan', '1234');
INSERT INTO `user` VALUES (19, '249', '黄岚', NULL, 2, '2141326130', 0, 'Lan', '1234');
INSERT INTO `user` VALUES (20, '302', '戴安琪', NULL, 1, '14830679799', 1, 'Anqi', '1234');
INSERT INTO `user` VALUES (21, '161', '阎嘉伦', NULL, 1, '14755078206', 0, 'Jialun', '1234');
INSERT INTO `user` VALUES (22, '287', '严睿', NULL, 2, '7553750132', 0, 'Rui', '1234');
INSERT INTO `user` VALUES (23, '585', '崔睿', NULL, 1, '16668631090', 0, 'Rui', '1234');
INSERT INTO `user` VALUES (24, '797', '方致远', NULL, 1, '7557106130', 0, 'Zhiyuan', '1234');
INSERT INTO `user` VALUES (25, '730', '郭震南', NULL, 1, '200514012', 1, 'Zhennan', '1234');
INSERT INTO `user` VALUES (26, '544', '卢詩涵', NULL, 1, '15235747199', 0, 'Shihan', '1234');
INSERT INTO `user` VALUES (27, '696', '钱宇宁', NULL, 1, '7698241542', 0, 'Yuning', '1234');
INSERT INTO `user` VALUES (28, '424', '沈秀英', NULL, 2, '76994345506', 1, 'Xiuying', '1234');
INSERT INTO `user` VALUES (29, '971', '韦杰宏', NULL, 1, '287154223', 0, 'Jiehong', '1234');
INSERT INTO `user` VALUES (30, '782', '范璐', NULL, 2, '2112829123', 0, 'Lu', '1234');
INSERT INTO `user` VALUES (31, '581', '王嘉伦', NULL, 1, '17380240421', 1, 'Jialun', '1234');

SET FOREIGN_KEY_CHECKS = 1;
