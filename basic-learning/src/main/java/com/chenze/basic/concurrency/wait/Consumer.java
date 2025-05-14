package com.chenze.basic.concurrency.wait;

/**
 * 这是一个消费者线程类，调用Buffer的remove()方法取出数据。当缓冲区空时，消费者线程调用wait()方法，等待有数据时再继续消费。
 */
public class Consumer implements Runnable {

    private final Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.remove();
                Thread.sleep(150); // 模拟消费的时间
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
