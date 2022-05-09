import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class ExcelUtilsTest2 {


    @Test
    public void 测试追加写入() {
        Map<String, Object> row1 = new LinkedHashMap<>();
        row1.put("姓名", "张三");
        row1.put("年龄", 23);
        row1.put("成绩", 88.32);
        row1.put("是否合格", true);
        row1.put("考试日期", DateUtil.date());

        Map<String, Object> row2 = new LinkedHashMap<>();
        row2.put("姓名", "李四");
        row2.put("年龄", 33);
        row2.put("成绩", 59.50);
        row2.put("是否合格", false);
        row2.put("考试日期", DateUtil.date());

        ArrayList<Map<String, Object>> rows = CollUtil.newArrayList(row1, row2);
        //创建文件
        String path = System.getProperty("user.dir") + File.separator + "files" + File.separator + "测试追加.xls";
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(path);
// 合并单元格后的标题行，使用默认标题样式
// 一次性写出内容，使用默认样式，强制输出标题
        writer.write(rows, true);


        Map<String, Object> row3 = new LinkedHashMap<>();
        row3.put("姓名3333333333", "张三");
        row3.put("年龄", 23);
        row3.put("成绩", 88.32);
        row3.put("是否合格", true);
        row3.put("考试日期", DateUtil.date());

        Map<String, Object> row4 = new LinkedHashMap<>();
        row4.put("姓名4444444444444444", "李四");
        row4.put("年龄", 33);
        row4.put("成绩", 59.50);
        row4.put("是否合格", false);
        row4.put("考试日期", DateUtil.date());
        ArrayList<Map<String, Object>> rows2 = CollUtil.newArrayList(row3, row4);


// 关闭writer，释放内存
        writer.close();


    }


}