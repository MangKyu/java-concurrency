package com.mangkyu.concurrency.java5;

import org.junit.jupiter.api.Test;

class RunnableTest {

    @Test
    void runnable() {
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

