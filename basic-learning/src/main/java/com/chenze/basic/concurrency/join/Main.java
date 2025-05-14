package com.chenze.basic.concurrency.join;

public class Main {

    public static void main(String[] args) {

        Thread1Runner threadRunner1 = new Thread1Runner();
        Thread2Runner threadRunner2 = new Thread2Runner(threadRunner1);
        Thread3Runner threadRunner3 = new Thread3Runner(threadRunner2);

        threadRunner3.start();
        threadRunner2.start();
        threadRunner1.start();
    }

}
