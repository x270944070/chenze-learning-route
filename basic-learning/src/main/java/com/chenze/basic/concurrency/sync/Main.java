package com.chenze.basic.concurrency.sync;

public class Main {

    public static int i = 0;


    private static void increment(){
        synchronized (Main.class) {
            i++;
        }
    }


    public static void main(String[] args) {
        increment();
    }

}
