package com.mangkyu.concurrency.java5;

public class ThreadMain {

    public static void main(String[] args) {
        start();
        run();
    }

    private static void run() {
        Thread thread = new MyThread();

        thread.run();
        thread.run();
        thread.run();

        System.out.println("Hello: " + Thread.currentThread().getName());
    }

    private static void start() {
        Thread thread = new MyThread();

        thread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }

}
