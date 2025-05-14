package com.chenze.redis.lock;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Main {


    // 假设@Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 使用单机的synchronized
     */
    public String businessMethod1(String businessId){
        String lockKey = "lock:product_" + businessId;
        synchronized (lockKey) { // 假设lockKey对应的对象在内存中是同一个
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get("stock")
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        }

        return "success";
    }



    /**
     * 使用Redis指令setnx设置值
     */
    public String businessMethod2(String businessId){
        String lockKey = "lock:product_" + businessId;

        /**
         * 1. setIfAbsent使用Redis的setnx命令，如果不存在key则设置成功，存在key设置失败返回false，表示已经有其他线程加锁
         * 2. 设置超时时间：如果在程序执行的过程中（比如当代码执行到第行时）Java程序崩溃，那么这个key就一直存在于Redis中而造成死锁
         * 3. 设置值的同时并设置超时时间而使用一条命令，保证了原子性，若分开：万一设置值之后程序崩溃那么也会出现问题2的情况造成死锁
         */
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "chenze", 8L, TimeUnit.SECONDS);
        if (!flag) {
            return "error";
        }

        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get("stock")
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 在finally中执行，保证在任何情况下（除非程序死掉）都会删除这个key，避免死锁
            stringRedisTemplate.delete(lockKey);
        }
        return "success";
    }



    /**
     * 使用Redis指令setnx设置值
     */
    public String businessMethod3(String businessId){
        String lockKey = "lock:product_" + businessId;

        /**
         * 1. setIfAbsent使用Redis的setnx命令，如果不存在key则设置成功，存在key设置失败返回false，表示已经有其他线程加锁
         * 2. 设置超时时间：如果在程序执行的过程中（比如当代码执行到第行时）Java程序崩溃，那么这个key就一直存在于Redis中而造成死锁
         * 3. 设置值的同时并设置超时时间而使用一条命令，保证了原子性，若分开：万一设置值之后程序崩溃那么也会出现问题2的情况造成死锁
         */
        String lockValue = UUID.randomUUID().toString();
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, 8L, TimeUnit.SECONDS);
        if (!flag) {
            return "error";
        }

        try {
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get("stock")
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key,value)
                System.out.println("扣减成功，剩余库存:" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 在finally中执行，保证在任何情况下（除非程序死掉）都会删除这个key，避免死锁
            if (lockValue.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "success";
    }


}
