package com.chenze.basic.concurrency.wait;

public class Main {

    public static void main(String[] args) {
        Buffer buffer = new Buffer(5);
        Thread producer1 = new Thread(new Producer(buffer));
        Thread producer2 = new Thread(new Producer(buffer));
        Thread consumer1 = new Thread(new Consumer(buffer));
        Thread consumer2 = new Thread(new Consumer(buffer));

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
    }

}
