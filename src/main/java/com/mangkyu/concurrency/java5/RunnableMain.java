package com.mangkyu.concurrency.java5;

public class RunnableMain {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread: " + Thread.currentThread().getName());
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        System.out.println("Hello: " + Thread.currentThread().getName());
    }

}
