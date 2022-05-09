CREATE TABLE `t_user`
(
    `id`          INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        VARCHAR(50) NOT NULL DEFAULT '0' COMMENT '用户名' COLLATE 'utf8mb4_bin',
    `create_time` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 测下划线命名',
    `updateTime`  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间 测驼峰和时间类型',
    `version`     INT(11) NOT NULL DEFAULT '0' COMMENT '乐观锁字段',
    `deleted`     TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0否 1是  测逻辑删除',
    PRIMARY KEY (`id`) USING BTREE
) COMMENT='测试用户'
COLLATE='utf8mb4_bin'
ENGINE=InnoDB
;
