package com.chenze.basic.concurrency.wait;

import java.util.LinkedList;
import java.util.List;

/**
 * 这是一个共享资源类，拥有一个固定大小的缓冲区。add()方法用于生产者添加数据，remove()方法用于消费者取出数据。两者都是同步方法，确保线程安全
 */
public class Buffer {

    private final int capacity;
    private final List<Integer> data = new LinkedList<>();

    public Buffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void add(int value) throws InterruptedException {
        while (data.size() == capacity) {
            System.out.println("Buffer is full. Producer is waiting...");
            wait(); // 等待缓冲区有空间
        }
        data.add(value);
        System.out.println("Produced: " + value);
        notifyAll(); // 唤醒所有等待的线程
    }

    public synchronized int remove() throws InterruptedException {
        while (data.isEmpty()) {
            System.out.println("Buffer is empty. Consumer is waiting...");
            wait(); // 等待缓冲区有数据
        }
        int value = data.remove(0);
        System.out.println("Consumed: " + value);
        notifyAll(); // 唤醒所有等待的线程
        return value;
    }

}
