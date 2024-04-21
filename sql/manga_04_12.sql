

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_black_list
-- ----------------------------
DROP TABLE IF EXISTS `bus_black_list`;
CREATE TABLE `bus_black_list` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `block_user_id` bigint(20) DEFAULT NULL COMMENT '屏蔽用户id',
                                  `block_user_name` varchar(255) DEFAULT NULL COMMENT '屏蔽用户名称',
                                  `is_deleted` varchar(255) DEFAULT '0' COMMENT '解除限制 0-未解除(不删除) 可以查看  1-解除(删除)-不可以查看\n',
                                  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                  `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                  `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                                  `updater` bigint(20) DEFAULT NULL COMMENT '更新人',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COMMENT='业务表_黑名单';

-- ----------------------------
-- Table structure for bus_browsing_history
-- ----------------------------
DROP TABLE IF EXISTS `bus_browsing_history`;
CREATE TABLE `bus_browsing_history` (
                                        `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                                        `coll_id` varchar(1024) DEFAULT NULL COMMENT '合集id',
                                        `coll_name` varchar(1024) DEFAULT NULL COMMENT '合集名称\n',
                                        `coll_cover` varchar(1024) DEFAULT NULL COMMENT '合集封面',
                                        `submission_id` varchar(1024) DEFAULT NULL COMMENT '稿件id',
                                        `submission_name` varchar(1024) DEFAULT NULL COMMENT '稿件名称',
                                        `submission_cover` varchar(1024) DEFAULT NULL COMMENT '稿件封面',
                                        `is_deleted` varchar(255) DEFAULT '0' COMMENT '字典状态 0- 默认不删除 1-删除\n',
                                        `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                        `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                        `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                                        `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
                                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='业务_浏览记录表';

-- ----------------------------
-- Table structure for bus_favorites
-- ----------------------------
DROP TABLE IF EXISTS `bus_favorites`;
CREATE TABLE `bus_favorites` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `coll_name` varchar(255) DEFAULT NULL COMMENT '合集名字',
                                 `coll_cover` varchar(1024) DEFAULT NULL COMMENT '合集封面',
                                 `coll_id` bigint(20) DEFAULT NULL COMMENT '合集id',
                                 `is_deleted` varchar(255) DEFAULT '0' COMMENT '0- 默认不删除 1-删除\n',
                                 `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                 `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                                 `updater` bigint(20) DEFAULT NULL COMMENT '更新人',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='业务_我的收藏';

-- ----------------------------
-- Table structure for bus_medal
-- ----------------------------
DROP TABLE IF EXISTS `bus_medal`;
CREATE TABLE `bus_medal` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `quantity` bigint(20) DEFAULT '0' COMMENT '勋章获取数量-粉丝值',
                             `name` varchar(255) DEFAULT NULL COMMENT '名称-粉丝等级',
                             `is_deleted` varchar(255) DEFAULT '0' COMMENT '0- 默认不删除 1-删除\n',
                             `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                             `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                             `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                             `updater` bigint(20) DEFAULT NULL COMMENT '更新人',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COMMENT='业务表_勋章表';

-- ----------------------------
-- Table structure for bus_privacy
-- ----------------------------
DROP TABLE IF EXISTS `bus_privacy`;
CREATE TABLE `bus_privacy` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `my_favorites` varchar(255) DEFAULT '0' COMMENT '我的收藏 0-隐藏 1-公开 默认为隐藏',
                               `my_subscription` varchar(255) DEFAULT '0' COMMENT '我的订阅 0-隐藏 1-公开,默认隐藏',
                               `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                               `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                               `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                               `updater` bigint(20) DEFAULT NULL COMMENT '更新人',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='业务表_隐私设置';

-- ----------------------------
-- Table structure for bus_subscription
-- ----------------------------
DROP TABLE IF EXISTS `bus_subscription`;
CREATE TABLE `bus_subscription` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                    `coll_id` bigint(20) DEFAULT NULL COMMENT '合集id',
                                    `coll_name` varchar(255) DEFAULT NULL COMMENT '合集名字',
                                    `is_deleted` varchar(255) DEFAULT NULL COMMENT '0- 默认不删除 1-删除\n',
                                    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                    `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                                    `updater` bigint(20) DEFAULT NULL COMMENT '更新人',
                                    `coll_cover` varchar(1024) DEFAULT NULL COMMENT '合集封面',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='业务表_订阅表';

SET FOREIGN_KEY_CHECKS = 1;
