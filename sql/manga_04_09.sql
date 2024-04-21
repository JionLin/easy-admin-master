
-- ----------------------------
-- Table structure for bus_coll
-- ----------------------------
DROP TABLE IF EXISTS `bus_coll`;
CREATE TABLE `bus_coll` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL COMMENT '名字',
                            `type` varchar(255) NOT NULL COMMENT '类型 0-短篇 1-热血 2-情感 3-剧情 4-轻松',
                            `cover` varchar(255) NOT NULL COMMENT '封面',
                            `introduction` varchar(1024) DEFAULT NULL COMMENT '简介',
                            `status` varchar(255) DEFAULT NULL COMMENT '状态  10- 待发布 11-已发布 12-审核中 13-未通过\n',
                            `favorites_count` bigint(20) DEFAULT '0' COMMENT '收藏数',
                            `read_count` bigint(20) DEFAULT '0' COMMENT '阅读数',
                            `chapters_count` bigint(20) DEFAULT NULL COMMENT '合集章节数',
                            `monthly_ticket_count` bigint(20) DEFAULT '0' COMMENT '月票总数量',
                            `comments_count` bigint(20) DEFAULT '0' COMMENT '评论数',
                            `complaints_count` bigint(20) DEFAULT '0' COMMENT '吐槽数量',
                            `shares_count` bigint(20) DEFAULT '0' COMMENT '分享数量',
                            `is_deleted` varchar(255) DEFAULT NULL COMMENT '是否删除 0-删除 1-未删除',
                            `publish_time` timestamp NULL DEFAULT NULL COMMENT '发布时间',
                            `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                            `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                            `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                            `updater` bigint(20) DEFAULT NULL COMMENT '更新人',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='业务合集表';

-- ----------------------------
-- Table structure for bus_coll_comment
-- ----------------------------
DROP TABLE IF EXISTS `bus_coll_comment`;
CREATE TABLE `bus_coll_comment` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                    `parent_id` bigint(20) DEFAULT NULL COMMENT '父id,第一级别默认为0,其余级别默认为当前评论上一级的id.',
                                    `content` varchar(1024) DEFAULT NULL COMMENT '评论内容,暂时允许中文,不允许图片',
                                    `comment_time` timestamp NULL DEFAULT NULL COMMENT '评论时间',
                                    `comment_likes` bigint(20) DEFAULT '0' COMMENT '评论点赞数',
                                    `coll_id` bigint(20) DEFAULT NULL COMMENT '合集id',
                                    `coll_name` varchar(1024) DEFAULT NULL COMMENT '合集名字',
                                    `is_deleted` varchar(255) DEFAULT NULL COMMENT '是否删除 0-删除 1-不删除',
                                    `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
                                    `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                                    `updater` bigint(20) DEFAULT NULL COMMENT '更新人',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='合集评论表';

-- ----------------------------
-- Table structure for bus_dict
-- ----------------------------
DROP TABLE IF EXISTS `bus_dict`;
CREATE TABLE `bus_dict` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                            `dict_code` varchar(1024) DEFAULT NULL COMMENT '字典编码',
                            `dict_name` varchar(1024) DEFAULT NULL COMMENT '字典名称',
                            `description` varchar(1024) DEFAULT NULL COMMENT '字典描述',
                            `dict_data` varchar(1024) DEFAULT NULL COMMENT '字典数据',
                            `is_deleted` varchar(255) DEFAULT NULL COMMENT '字典状态',
                            `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                            `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                            `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                            `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='业务字典表';



-- ----------------------------
-- Table structure for bus_submission
-- ----------------------------
DROP TABLE IF EXISTS `bus_submission`;
CREATE TABLE `bus_submission` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `title` varchar(1024) DEFAULT NULL COMMENT '标题',
                                  `summary` varchar(1024) DEFAULT NULL COMMENT '简介\n',
                                  `coll_id` bigint(20) DEFAULT NULL COMMENT '合集id',
                                  `coll_name` varchar(1024) DEFAULT NULL COMMENT '合集名称',
                                  `cover` varchar(1024) DEFAULT NULL COMMENT '封面',
                                  `status` varchar(255) DEFAULT NULL COMMENT '0-已发布 1-待审核 2-未通过',
                                  `number_of_chapter` varchar(255) DEFAULT NULL COMMENT '进行展示多少话',
                                  `read_count` bigint(20) DEFAULT '0' COMMENT '阅读数',
                                  `monthly_ticket_count` bigint(20) DEFAULT '0' COMMENT '月票数',
                                  `favorites_count` bigint(20) DEFAULT '0' COMMENT '收藏数',
                                  `complaints_count` bigint(20) DEFAULT '0' COMMENT '吐槽数',
                                  `shares_count` bigint(20) DEFAULT '0' COMMENT '分享数',
                                  `is_deleted` varchar(255) DEFAULT NULL COMMENT '是否删除 0-删除 1- 未删除',
                                  `create_time` timestamp NULL DEFAULT NULL,
                                  `update_time` timestamp NULL DEFAULT NULL,
                                  `creator` bigint(20) DEFAULT NULL,
                                  `updater` bigint(20) DEFAULT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='稿件表';

-- ----------------------------
-- Table structure for bus_submission_comment
-- ----------------------------
DROP TABLE IF EXISTS `bus_submission_comment`;
CREATE TABLE `bus_submission_comment` (
                                          `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                          `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
                                          `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
                                          `comment_time` timestamp NULL DEFAULT NULL COMMENT '评论时间',
                                          `comment_likes` bigint(20) DEFAULT NULL COMMENT '评论点赞数',
                                          `submission_id` bigint(20) DEFAULT NULL COMMENT '所属稿件id',
                                          `submission_name` varchar(1024) DEFAULT NULL COMMENT '所属稿件名称',
                                          `is_deleted` varchar(255) DEFAULT NULL COMMENT '是否删除 0-删除 1-未删除',
                                          `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
                                          `update_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
                                          `creator` bigint(20) DEFAULT NULL COMMENT '创建人',
                                          `updater` bigint(20) DEFAULT NULL COMMENT '修改人',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='稿件评论表';

-- ----------------------------
-- Table structure for bus_submission_image
-- ----------------------------
DROP TABLE IF EXISTS `bus_submission_image`;
CREATE TABLE `bus_submission_image` (
                                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                        `submission_id` bigint(20) DEFAULT NULL COMMENT '所属稿件',
                                        `url` varchar(1024) DEFAULT NULL COMMENT '对应路径',
                                        `position` bigint(20) DEFAULT NULL COMMENT '顺序位置',
                                        `is_deleted` varchar(255) DEFAULT NULL COMMENT '是否删除 0- 已删除 1- 未删除',
                                        `creator` bigint(20) DEFAULT NULL,
                                        `updater` bigint(20) DEFAULT NULL,
                                        `create_time` timestamp NULL DEFAULT NULL,
                                        `update_time` timestamp NULL DEFAULT NULL,
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='稿件图片信息表';
