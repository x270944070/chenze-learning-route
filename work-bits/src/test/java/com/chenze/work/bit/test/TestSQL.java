package com.chenze.work.bit.test;

import cn.hutool.core.io.FileUtil;
import com.chenze.common.util.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class TestSQL {


    @Test
    public void test8() throws Exception{
        File file = FileUtil.file("C:\\Users\\chenze\\Desktop\\无标题电子表格.xlsx");
        List<ReadExcelTemplate2> readExcelTemplate2List = ExcelUtil.readExcel(file, ReadExcelTemplate2.class);

        for (ReadExcelTemplate2 read : readExcelTemplate2List) {

            String sql = "insert into tp_sa_business_product(product_name, product_type, product_code) value ('%s','%s','%s');";
            System.out.println(String.format(sql, read.getName(), read.getType(), read.getCode()));
        }

    }

}
