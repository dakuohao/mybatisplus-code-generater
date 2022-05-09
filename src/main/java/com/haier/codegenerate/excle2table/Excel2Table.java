package com.haier.codegenerate.excle2table;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * excel工具箱
 */
public class Excel2Table {
    private static final Db db = Db.use("zhilian_order_entrance");

    /**
     * excel文件转为数据库的table
     */
    public static void main(String[] args) throws SQLException {
        //定义变量
        String tableName = "order_detail";//表名
        String tableComment = "订单详情表";//表注释
        String path = "C:\\WorkSpace\\my-study\\code-generater\\src\\main\\java\\com\\haier\\excle2table\\excel2table.xlsx";

        createTable(tableName, tableComment, path);
    }

    private static void createTable(String tableName, String tableComment, String path) throws SQLException {
        //1.创建表
        String dropSql = "DROP TABLE " + tableName + ";";
        String createSql = "CREATE TABLE `" + tableName + "` (\n" +
                "\t`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',\n" +
                "\t`create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',\n" +
                "\t`update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',\n" +
                "\t`deleted` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0否 1是 ',\n" +
                "\tPRIMARY KEY (`id`) USING BTREE\n" +
                ")\n" +
                "COMMENT='" + tableComment + "'\n" +
                "COLLATE='utf8mb4_bin'\n" +
                "ENGINE=InnoDB\n" +
                ";\n";
        List<Entity> showTables = db.query("SHOW TABLES LIKE  '%" + tableName + "%';");
        if (ObjectUtil.isNotEmpty(showTables) && contains(showTables,tableName)) {
            db.execute(dropSql);
        }
        db.execute(createSql);
        //2. 添加字段
        ExcelReader reader = ExcelUtil.getReader(path);
        List<Map<String, Object>> list = reader.readAll();
        Collections.reverse(list);//倒序 保证表的顺序跟excel一致
        for (Map<String, Object> map : list) {
            if (ObjectUtil.isNotEmpty(map)) {
                String fieldName = mapGet(map, "名称");
                String fieldComment = mapGet(map, "描述");
                if (StrUtil.isNotBlank(fieldName)) {
                    String alterSql = "ALTER TABLE `" + tableName + "` ADD COLUMN `" + getFieldName(fieldName) + "` VARCHAR(100) NULL DEFAULT '' COMMENT '" + fieldComment + "' AFTER `id`;";
                    db.execute(alterSql);
                }
            }
        }
    }

    private static boolean contains(List<Entity> showTables, String tableName) {
        for (Entity entity : showTables) {
            if(entity.containsValue(tableName)){
                return true;
            }
        }
        return false;
    }

    private static String getFieldName(String fieldName) {
        //todo 中文自动翻译
//        boolean chineseChar = isChineseChar(fieldName);
//        if(chineseChar){
//
//        }
        return StrUtil.toUnderlineCase(fieldName);
    }

    /**
     * 判断一个字符是否是汉字
     * PS：中文汉字的编码范围：[\u4e00-\u9fa5]
     *
     * @param str) 需要判断的字符
     * @return 是汉字(true), 不是汉字(false)
     */

    public static boolean isChineseChar(String str) {
        return str.matches("[\u4e00-\u9fa5]");
    }

    /**
     * 获取map的key的值，转为字符串并去空格
     *
     * @param map map对象
     * @param key key
     * @return 转为字符串并去空格后的字符
     */
    private static String mapGet(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (ObjectUtil.isNotEmpty(value) || !"null".equals(value)) {
            return value.toString().trim();
        }
        return "";
    }


}
