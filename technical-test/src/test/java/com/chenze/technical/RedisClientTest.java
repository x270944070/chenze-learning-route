package com.chenze.technical;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisClientTest {



    public static void main(String[] args) {
        // 创建 Jedis 实例，并提供密码
        Jedis jedis = new Jedis("114.96.67.201", 6380);  // Redis 服务器地址和端口
        jedis.auth("Cz0011");  // 连接时提供密码

        // 检查连接是否成功
        String pingResponse = jedis.ping();
        System.out.println("Ping response: " + pingResponse);  // 应该输出 "PONG"

        // 关闭连接
        jedis.close();
    }

}
