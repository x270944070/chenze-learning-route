package com.chenze.basic.concurrency.wait;

/**
 * 这是一个生产者线程类，调用Buffer的add()方法添加数据。当缓冲区满时，生产者线程调用wait()方法，等待有空间时再继续生产
 */
public class Producer implements Runnable {
    private final Buffer buffer;

    public Producer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.add(i);
                Thread.sleep(100); // 模拟生产的时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
