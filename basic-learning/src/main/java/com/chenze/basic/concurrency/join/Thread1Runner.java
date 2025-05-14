package com.chenze.basic.concurrency.join;

public class Thread1Runner extends Thread {

    @Override
    public void run() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I am Thread1Runner");
    }
}
