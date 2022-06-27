package com.mangkyu.concurrency.java5;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CallableTest {

    @Test
    void callable_void() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<Void> callable = new Callable<Void>() {
            @Override
            public Void call() {
                final String result = "Thread: " + Thread.currentThread().getName();
                System.out.println(result);
                return null;
            }
        };

        executorService.submit(callable);
        executorService.shutdown();
    }


    @Test
    void callable_String() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                return "Thread: " + Thread.currentThread().getName();
            }
        };

        executorService.submit(callable);
        executorService.shutdown();
    }


}
