package com.chenze.basic.concurrency.join;

public class Thread3Runner extends Thread {

    private final Thread priorityThread;

    public Thread3Runner(Thread priorityThread) {
        this.priorityThread = priorityThread;
    }

    @Override
    public void run() {
        try {
            sleep(2000);
            priorityThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("I am Thread3Runner");
    }
}
