package com.chenze.work.bit.test;

import cn.hutool.core.io.FileUtil;
import com.chenze.common.util.ExcelUtil;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

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

    @Test
    public void test1(){
// 创建 Jedis 实例，连接到本地 Redis 服务
        Jedis jedis = new Jedis("localhost", 6379); // 或者使用容器的 IP 地址

        // 打印 Redis 服务是否连接成功
        System.out.println("Connected to Redis server: " + jedis.ping());

        // 设置一个键值对
        jedis.set("name", "Redis");

        // 获取设置的值
        String value = jedis.get("name");
        System.out.println("Stored value in Redis: " + value);

        // 设置带过期时间的键值对（秒为单位）
        jedis.setex("tempKey", 10, "This is temporary data");

        // 获取带过期时间的键
        String tempValue = jedis.get("tempKey");
        System.out.println("Temporary stored value: " + tempValue);

        // 关闭 Jedis 连接
        jedis.close();
    }

}
