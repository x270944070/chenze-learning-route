package com.chenze.basic.concurrency.test;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如何用两个线程轮流输出0到200的值？
 */
public class ZeroGoTwoHundredTest {

    private static AtomicInteger num1 = new AtomicInteger(0);
    private static volatile boolean flag = true;

    /**
     * 方案1：使用一个公共变量，控制逻辑的走向
     */
    @Test
    public void test1() throws Exception {
        new Thread(() -> {
            while (num1.get() < 200) {
                if (flag) {
                    System.out.println(Thread.currentThread().getName() + "\t" + num1.incrementAndGet());
                    flag = false;
                }
            }
        }).start();

        new Thread(() -> {
            while (num1.get() < 200) {
                if (!flag) {
                    System.out.println(Thread.currentThread().getName() + "\t" + num1.incrementAndGet());
                    flag = true;
                }
            }
        }).start();

        Thread.sleep(2000);
    }


    /**
     * 方案2：使用多线程同步机制实现
     */
    private final Object object = new Object();

    @Test
    public void test2() throws Exception {

        /**
         * notify：通知的是 在同一个对象上调用过 wait() 的线程
         * wait：当前线程主动暂停自己，挂起并释放锁，等待别人唤醒它
         */

        // 假设runnable1先执行
        Runnable runnable1 = () -> {
            while (num1.get() < 200) {
                // 第一次，没有任何线程持有object，直接进入逻辑中
                synchronized (object) {
                    // 尝试唤醒在 object 上等待的另一个线程（如果有）
                    // 但被唤醒的线程要等当前线程（runnable1）退出 synchronized 才能获得锁继续执行
                    object.notify();
                    System.out.println(Thread.currentThread().getName() + "\t" + num1.incrementAndGet());
                    try {
                        // 当前线程挂起，并释放 object 的锁
                        // 此时如果另一个线程被 notify 唤醒，就可以获得锁继续执行
                        //
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        Runnable runnable2 = () -> {
            while (num1.get() < 200) {
                // 如果 object 已被 runnable1 锁住，此线程会在进入 synchronized 时阻塞
                // 当 runnable1 执行 wait() 后释放锁，本线程才能获得锁并继续执行
                synchronized (object) {
                    // 和runnable1中的逻辑一样
                    // 尝试唤醒在 object 上等待的另一个线程（可能是 runnable1）
                    // 但被唤醒线程要等当前线程执行完 synchronized 才能继续
                    object.notify();
                    System.out.println(Thread.currentThread().getName() + "\t" + num1.incrementAndGet());
                    try {
                        // 当前线程挂起，并释放 object 锁
                        // 被唤醒的线程（runnable1）此时可能已在等待锁，会获得锁继续执行
                        // 如果没有执行过notify，将一直等待
                        object.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        // 为了方便学习与理解，这里并不服用上述的runnable，而是单独写两个
        new Thread(runnable1, "thread-0").start();
        new Thread(runnable2, "thread-1").start();

        Thread.sleep(2000);
    }
}
