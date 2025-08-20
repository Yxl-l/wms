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

 Date: 20/08/2025 23:10:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for branch_table
-- ----------------------------
DROP TABLE IF EXISTS `branch_table`;
CREATE TABLE `branch_table`  (
  `branch_id` bigint NOT NULL,
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `transaction_id` bigint NULL DEFAULT NULL,
  `resource_group_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `branch_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint NULL DEFAULT NULL,
  `client_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime(6) NULL DEFAULT NULL,
  `gmt_modified` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`branch_id`) USING BTREE,
  INDEX `idx_xid`(`xid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of branch_table
-- ----------------------------

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
-- Table structure for distributed_lock
-- ----------------------------
DROP TABLE IF EXISTS `distributed_lock`;
CREATE TABLE `distributed_lock`  (
  `lock_key` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lock_value` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `expire` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`lock_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of distributed_lock
-- ----------------------------
INSERT INTO `distributed_lock` VALUES ('AsyncCommitting', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('RetryCommitting', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('RetryRollbacking', ' ', 0);
INSERT INTO `distributed_lock` VALUES ('TxTimeoutCheck', ' ', 0);

-- ----------------------------
-- Table structure for global_table
-- ----------------------------
DROP TABLE IF EXISTS `global_table`;
CREATE TABLE `global_table`  (
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `transaction_id` bigint NULL DEFAULT NULL,
  `status` tinyint NOT NULL,
  `application_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `transaction_service_group` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `transaction_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `timeout` int NULL DEFAULT NULL,
  `begin_time` bigint NULL DEFAULT NULL,
  `application_data` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`xid`) USING BTREE,
  INDEX `idx_status_gmt_modified`(`status` ASC, `gmt_modified` ASC) USING BTREE,
  INDEX `idx_transaction_id`(`transaction_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of global_table
-- ----------------------------

-- ----------------------------
-- Table structure for lock_table
-- ----------------------------
DROP TABLE IF EXISTS `lock_table`;
CREATE TABLE `lock_table`  (
  `row_key` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `xid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `transaction_id` bigint NULL DEFAULT NULL,
  `branch_id` bigint NOT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `table_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pk` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0:locked ,1:rollbacking',
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`row_key`) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE,
  INDEX `idx_branch_id`(`branch_id` ASC) USING BTREE,
  INDEX `idx_xid`(`xid` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lock_table
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品SKU表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sku
-- ----------------------------
INSERT INTO `sku` VALUES (1, 299.00, 399.00, 10, 'cxk123456', '蔡徐坤的卫衣', 1, 6, 40, 50, 20, 40000, '0', '11', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (2, 288.00, 366.00, 10, 'cxk123457', '蔡徐坤的卫衣', 1, 6, 40, 50, 20, 40000, '0', '12', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (3, 998.00, 366.00, 11, 'cxk123457', '蔡徐坤的裙子', 1, 6, 40, 50, 20, 40000, '0', '13', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (4, 788.00, 366.00, 11, 'cxk123457', '蔡徐坤的裙子', 1, 6, 40, 50, 20, 50000, '0', '14', '2025-08-08 17:13:17', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (5, 3.00, 3.00, 12, 'wazi123', '精品袜子', 3, 3, 3, 3, 3, 27, '3', '3', '2025-08-09 08:38:56', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (6, 3.00, 3.00, 12, 'wazi124', '精品袜子', 3, 3, 3, 3, 3, 27, '3', '3', '2025-08-09 08:38:56', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (7, 669.00, 399.00, 14, 'banxie11', '板鞋', 2, 50, 30, 30, 20, 18000, '1', '2', '2025-08-10 08:30:28', NULL, NULL, NULL, 1);
INSERT INTO `sku` VALUES (8, 23.00, 10.00, 15, 'neiku123', '金丝内裤', 1, 8, 4, 4, 4, 64, '6', '33', '2025-08-10 08:56:28', NULL, NULL, NULL, 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品Spu表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of spu
-- ----------------------------
INSERT INTO `spu` VALUES (10, '蔡徐坤的卫衣', '中性', 14, 11, NULL, '男女通用', '<p>222</p>', 999, 5, '', '2025-08-08 17:13:17', NULL, '2025-08-08 17:13:17', NULL, 1);
INSERT INTO `spu` VALUES (11, '蔡徐坤的裙子', 'cxk', 7, NULL, NULL, '裙子', '<p>222</p>', 999, 1, '', '2025-08-08 17:13:17', NULL, '2025-08-08 17:13:17', NULL, 1);
INSERT INTO `spu` VALUES (12, '精品袜子', '小短袜', 13, 9, NULL, 'qqqqqqqqqqqqqqqq', '<p>ayifgcbiysVSIdgvfcayuwegfaifawegwaewgfqeqefqe</p>', 2, 7, NULL, '2025-08-09 08:38:56', NULL, '2025-08-09 08:38:56', NULL, 1);
INSERT INTO `spu` VALUES (13, '风衣', 'qq', 11, 12, 21, '11', '<p>qqq</p>', 1, 3, NULL, '2025-08-09 08:45:07', NULL, '2025-08-09 08:45:07', NULL, 1);
INSERT INTO `spu` VALUES (14, '空军板鞋', '板鞋', 9, 21, NULL, '板鞋', '<p>板鞋</p>', 21, 2, NULL, '2025-08-10 08:30:28', NULL, '2025-08-10 08:30:28', NULL, 1);
INSERT INTO `spu` VALUES (15, '金丝内裤', '内裤', 15, 8, NULL, '内裤', '<p>内裤内裤内裤内裤内裤内裤内裤内裤内裤内裤内裤内裤内裤内裤</p>', 999, 7, NULL, '2025-08-10 08:56:28', NULL, '2025-08-10 08:56:28', NULL, 1);

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
-- Table structure for tb_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `tb_dictionary`;
CREATE TABLE `tb_dictionary`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典类型',
  `dict_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典编码',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '字典值',
  `sort_order` int NULL DEFAULT 1 COMMENT '排序',
  `is_active` tinyint NULL DEFAULT 1 COMMENT '是否启用(1:启用, 0:禁用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_dictionary
-- ----------------------------
INSERT INTO `tb_dictionary` VALUES (1, 'WAREHOUSE_AREA_CATEGORY', 'RECEIVING_AREA', '收货区', 1, 1);
INSERT INTO `tb_dictionary` VALUES (2, 'WAREHOUSE_AREA_CATEGORY', 'STORAGE_AREA', '存储区', 2, 1);
INSERT INTO `tb_dictionary` VALUES (3, 'WAREHOUSE_AREA_CATEGORY', 'PICKING_AREA', '拣货区', 3, 1);
INSERT INTO `tb_dictionary` VALUES (4, 'WAREHOUSE_AREA_CATEGORY', 'PACKING_AREA', '打包区', 4, 1);
INSERT INTO `tb_dictionary` VALUES (5, 'WAREHOUSE_AREA_CATEGORY', 'SHIPPING_AREA', '发货区', 5, 1);

-- ----------------------------
-- Table structure for tb_logistics_company
-- ----------------------------
DROP TABLE IF EXISTS `tb_logistics_company`;
CREATE TABLE `tb_logistics_company`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `company_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '物流公司名字',
  `min_price` int NOT NULL DEFAULT 1000 COMMENT '物流运输最低费用',
  `link_user` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '联系人',
  `linke_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '联系电话',
  `sort` int NOT NULL DEFAULT 1 COMMENT '排序',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '物流公司表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_logistics_company
-- ----------------------------
INSERT INTO `tb_logistics_company` VALUES (1, '鸡哥物流🏀', 13, '蔡徐坤', '1580110', 1, 'cxk123', '2025-08-15 22:25:41', NULL, NULL, NULL, 1);
INSERT INTO `tb_logistics_company` VALUES (2, '顺丰速运', 18, '顺丰校长', '19875488544', 1, 'cxk123', '2025-08-15 22:41:58', NULL, NULL, NULL, 1);
INSERT INTO `tb_logistics_company` VALUES (3, '圆通111', 14, '圆通小院', '1874554', 1, 'cxk123', '2025-08-15 22:42:24', NULL, NULL, NULL, 1);
INSERT INTO `tb_logistics_company` VALUES (4, '中国邮政', 13, '天启绿骑士', '1874684', 1, 'cxk123', '2025-08-15 22:43:26', NULL, NULL, NULL, 1);
INSERT INTO `tb_logistics_company` VALUES (5, '鸡兔快递', 13, '小兔', '1847844', 1, 'cxk123', '2025-08-15 22:51:17', NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for tb_logistics_price
-- ----------------------------
DROP TABLE IF EXISTS `tb_logistics_price`;
CREATE TABLE `tb_logistics_price`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `cube_price` int NOT NULL DEFAULT 0 COMMENT '每立方费用（单位：分）',
  `kilogram_price` int NOT NULL DEFAULT 0 COMMENT '每千克费用（单位：分）',
  `trans_kilometer_price` int NOT NULL DEFAULT 0 COMMENT '每千米运输费用',
  `category` int NOT NULL COMMENT '类型(1 同城、2 同省、3 跨省、4 经济区互寄)',
  `logistics_company_id` int NOT NULL DEFAULT 0 COMMENT '关联物流公司',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '物流价格表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_logistics_price
-- ----------------------------

-- ----------------------------
-- Table structure for tb_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse`;
CREATE TABLE `tb_warehouse`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '仓库名字',
  `city_id` int NOT NULL COMMENT '所在城市ID',
  `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '所在城市名称',
  `province_id` int NOT NULL COMMENT '所在省份ID',
  `province_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '所在省份名字',
  `address` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '详细地址',
  `location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '经纬度',
  `person_charge_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '负责人名字',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `person_charge_id` int NOT NULL COMMENT '负责人ID',
  `sort` int NOT NULL DEFAULT 1 COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '仓库信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_warehouse
-- ----------------------------
INSERT INTO `tb_warehouse` VALUES (1, '北京朝阳主仓库', 110105, '北京市', 110000, '北京市', '朝阳区建国路88号', '39.9087,116.4815', '张朝阳', 'SAAS001', 1001, 1, '2025-01-10 09:00:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (2, '北京西城分仓', 110102, '北京市', 110000, '北京市', '西城区金融大街15号', '39.9138,116.3585', '李西城', 'SAAS001', 1002, 2, '2025-01-15 10:30:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (3, '上海黄浦中心仓', 310101, '上海市', 310000, '上海市', '黄浦区南京东路233号', '31.2356,121.4850', '王黄埔', 'SAAS001', 1003, 1, '2025-02-05 14:20:00', 'admin', '2025-03-10 11:00:00', 'admin', 1);
INSERT INTO `tb_warehouse` VALUES (4, '上海静安冷链仓', 310105, '上海市', 310000, '上海市', '静安区江宁路358号', '31.2297,121.4594', '赵静安', 'SAAS002', 1004, 2, '2025-02-20 16:45:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (5, '杭州上城电商仓', 330102, '杭州市', 330000, '浙江省', '上城区解放路18号', '30.2445,120.1715', '陈上城', 'SAAS002', 1005, 1, '2025-03-01 08:15:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (6, '杭州拱墅保税仓', 330105, '杭州市', 330000, '浙江省', '拱墅区湖墅南路368号', '30.2906,120.1492', '林拱墅', 'SAAS002', 1006, 2, '2025-03-12 13:30:00', 'admin', '2025-04-05 10:20:00', 'admin', 1);
INSERT INTO `tb_warehouse` VALUES (7, '广州荔湾服装仓', 440103, '广州市', 440000, '广东省', '荔湾区中山八路56号', '23.1209,113.2384', '黄荔湾', 'SAAS003', 1007, 1, '2025-04-02 11:10:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (8, '广州越秀食品仓', 440104, '广州市', 440000, '广东省', '越秀区东风中路363号', '23.1252,113.2644', '吴越秀', 'SAAS003', 1008, 2, '2025-04-18 15:45:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (9, '深圳罗湖跨境仓', 440301, '深圳市', 440000, '广东省', '罗湖区人民南路2028号', '22.5393,114.1239', '郑罗湖', 'SAAS003', 1009, 1, '2025-05-05 10:00:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (10, '深圳南山科技仓', 440303, '深圳市', 440000, '广东省', '南山区科技南一路8号', '22.5319,113.9445', '周南山', 'SAAS003', 1010, 2, '2025-05-20 14:30:00', 'admin', '2025-06-10 09:15:00', 'admin', 1);
INSERT INTO `tb_warehouse` VALUES (11, '成都锦江生鲜仓', 510104, '成都市', 510000, '四川省', '锦江区红星路三段16号', '30.6561,104.0815', '刘锦江', 'SAAS004', 1011, 1, '2025-06-08 09:45:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (12, '成都青羊电器仓', 510105, '成都市', 510000, '四川省', '青羊区人民中路一段6号', '30.6697,104.0634', '孙青羊', 'SAAS004', 1012, 2, '2025-06-22 16:20:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (13, '十堰郧阳汽车仓', 3, '十堰市', 1, '湖北省', '郧阳区解放路25号', '32.8349,110.8126', '钱郧阳', 'SAAS005', 1013, 1, '2025-07-10 08:30:00', 'admin', NULL, NULL, 1);
INSERT INTO `tb_warehouse` VALUES (14, '北京京✌仓库', 110101, '东城区', 110000, '北京市', '北京东城区强强强强去', '11', '蔡徐坤', 'cxk', 158, 1, '2025-08-14 21:09:35', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_warehouse_area
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse_area`;
CREATE TABLE `tb_warehouse_area`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '库区名字',
  `warehouse_id` int NOT NULL COMMENT '所属仓库ID',
  `category` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '库区类型(字典数据)',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '仓库库区表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_warehouse_area
-- ----------------------------
INSERT INTO `tb_warehouse_area` VALUES (1, '一号库区', 1, 'RECEIVING_AREA', NULL, '2025-08-15 00:47:25', NULL, NULL, NULL, NULL);
INSERT INTO `tb_warehouse_area` VALUES (2, '二号', 1, 'PICKING_AREA', NULL, '2025-08-15 00:49:08', NULL, NULL, NULL, NULL);
INSERT INTO `tb_warehouse_area` VALUES (3, '京✌三号', 2, 'PICKING_AREA', NULL, '2025-08-15 01:22:30', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_warehouse_area_loca
-- ----------------------------
DROP TABLE IF EXISTS `tb_warehouse_area_loca`;
CREATE TABLE `tb_warehouse_area_loca`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `area_id` bigint NOT NULL COMMENT '所属库区id',
  `loca_code` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '库位编号',
  `loca_len` int NOT NULL COMMENT '库位长度',
  `loca_width` int NOT NULL COMMENT '库位宽度',
  `loca_height` int NOT NULL COMMENT '库位高度',
  `local_volume` int NOT NULL COMMENT '库位容积',
  `local_bearing_capacity` int NOT NULL COMMENT '库位承重',
  `tunnel_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '巷道号',
  `shelf_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '货架号',
  `layer_number` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '层号',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '库位表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_warehouse_area_loca
-- ----------------------------
INSERT INTO `tb_warehouse_area_loca` VALUES (1, 1, '1', 12, 12, 12, 1222, 1111, '3', '123', '2', NULL, '2025-08-18 21:30:05', NULL, NULL, NULL, NULL);
INSERT INTO `tb_warehouse_area_loca` VALUES (2, 2, '3', 3, 3, 3, 33, 33, '3', '3', '3', NULL, '2025-08-18 21:30:44', NULL, NULL, NULL, NULL);

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
INSERT INTO `user` VALUES (2, '388', '芙芙', NULL, 1, '19546996445', 1, 'fufu', '1234');
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

-- ----------------------------
-- Table structure for warehousing_entry
-- ----------------------------
DROP TABLE IF EXISTS `warehousing_entry`;
CREATE TABLE `warehousing_entry`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `consignor_id` bigint NOT NULL COMMENT '货主ID',
  `batch_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '批次号',
  `total_count` int NOT NULL COMMENT '批次商品数量',
  `predicted_arrival_time` datetime NOT NULL COMMENT '预测到达时间',
  `warehouse_id` int NOT NULL COMMENT '仓库ID',
  `damage_count` int NOT NULL DEFAULT 0 COMMENT '破损个数',
  `surpass_count` int NOT NULL DEFAULT 0 COMMENT '超出数量',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态(1 未确认，2 部分已确认，3 全部确认、 4 拣货中、5 已上架)',
  `lack_count` int NOT NULL DEFAULT 0 COMMENT '缺少数量',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '入库单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehousing_entry
-- ----------------------------
INSERT INTO `warehousing_entry` VALUES (1, 1, '1113', 10000, '2025-08-12 00:00:00', 2, 0, 0, 1, 0, NULL, '2025-08-18 23:08:26', NULL, '2025-08-20 21:29:18', NULL, NULL);
INSERT INTO `warehousing_entry` VALUES (2, 22, '222', 6666, '2025-08-04 00:00:00', 3, 9, 0, 3, 9, NULL, '2025-08-18 23:17:24', NULL, NULL, NULL, NULL);
INSERT INTO `warehousing_entry` VALUES (3, 1, '3', 3, '2025-08-12 00:00:00', 2, 3, 3, 4, 3, NULL, '2025-08-19 00:45:57', NULL, '2025-08-20 23:03:04', NULL, NULL);

-- ----------------------------
-- Table structure for warehousing_entry_details
-- ----------------------------
DROP TABLE IF EXISTS `warehousing_entry_details`;
CREATE TABLE `warehousing_entry_details`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sku_id` bigint NOT NULL COMMENT '规格ID',
  `count` int NOT NULL COMMENT '数量',
  `supplier_id` bigint NOT NULL COMMENT '供应商ID(默认选中，但允许灵活修改)',
  `price` int NOT NULL COMMENT '单价(默认查询，但允许灵活修改)',
  `warehousing_entry_id` bigint NOT NULL COMMENT '入库单ID',
  `real_arrival_time` datetime NULL DEFAULT NULL COMMENT '实际到达时间',
  `predicted_arrival_time` datetime NOT NULL COMMENT '预测到达时间',
  `unload_emp_id` int NULL DEFAULT NULL COMMENT '卸货员工ID',
  `unload_time` datetime NULL DEFAULT NULL COMMENT '卸货时间',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态(1 未到货，2 已到货，3 已卸货，4 分拣中，5 完成分拣)',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '入库明细表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehousing_entry_details
-- ----------------------------
INSERT INTO `warehousing_entry_details` VALUES (1, 1, 124124125, 2, 666, 3, '2025-08-26 00:00:00', '2025-08-19 00:56:20', 3, '2025-08-18 00:00:00', 3, NULL, '2025-08-19 00:56:30', NULL, '2025-08-20 23:04:48', NULL, NULL);
INSERT INTO `warehousing_entry_details` VALUES (2, 5, 99999, 7, 99, 2, '2025-08-29 00:00:00', '2025-08-29 00:00:00', 90, '2025-08-24 00:00:00', 2, NULL, '2025-08-19 08:57:07', NULL, '2025-08-20 16:32:36', NULL, NULL);
INSERT INTO `warehousing_entry_details` VALUES (3, 7, 333, 3, 299, 2, '2025-08-04 00:00:00', '2025-08-20 00:00:00', 1, '2025-08-21 00:00:00', 3, NULL, '2025-08-20 16:32:24', NULL, '2025-08-20 23:04:52', NULL, NULL);
INSERT INTO `warehousing_entry_details` VALUES (4, 8, 33, 7, 56, 3, '2025-07-29 00:00:00', '2025-08-13 00:00:00', 1, '2025-08-18 00:00:00', 4, NULL, '2025-08-20 22:48:51', NULL, '2025-08-20 22:49:16', NULL, NULL);

-- ----------------------------
-- Table structure for warehousing_entry_details_sort
-- ----------------------------
DROP TABLE IF EXISTS `warehousing_entry_details_sort`;
CREATE TABLE `warehousing_entry_details_sort`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `count` int NOT NULL COMMENT '分拣数量',
  `warehousing_entry_details_id` bigint NOT NULL COMMENT '入库单明细ID',
  `sku_id` bigint NOT NULL COMMENT '规格编号',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态(1 分拣中，2 已完成分拣，3 已上架)',
  `damage_count` int NOT NULL DEFAULT 0 COMMENT '破损个数',
  `surpass_count` int NOT NULL DEFAULT 0 COMMENT '超出数量',
  `lack_count` int NOT NULL DEFAULT 0 COMMENT '缺少数量',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '入库明细分拣表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehousing_entry_details_sort
-- ----------------------------

-- ----------------------------
-- Table structure for warehousing_entry_up
-- ----------------------------
DROP TABLE IF EXISTS `warehousing_entry_up`;
CREATE TABLE `warehousing_entry_up`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `sku_id` bigint NOT NULL COMMENT '规格ID',
  `warehouse_id` int NOT NULL COMMENT '上架仓库ID',
  `warehouse_area_id` int NOT NULL COMMENT '上架库区ID',
  `warehouse_area_loca_id` int NOT NULL COMMENT '上架库位ID',
  `count` int NOT NULL COMMENT '上架数量',
  `details_sort_id` bigint NOT NULL COMMENT '分拣明细ID',
  `status` int NOT NULL DEFAULT 1 COMMENT '上架状态(1 未上架，2 已上架)',
  `saas_id` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'SaaS标识',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '创建人账号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人账号',
  `is_del` int NULL DEFAULT NULL COMMENT '是否删除  1 未删除  2 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '入库上架表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehousing_entry_up
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
