package com.mangkyu.concurrency.java8;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CompletableFutureRunTest {

    @Test
    void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
        });

        future.get();
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    @Test
    void supplyAsync() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Thread: " + Thread.currentThread().getName();
        });

        System.out.println(future.get());
        System.out.println("Thread: " + Thread.currentThread().getName());
    }

    @Test
    void supplyAsyncAnotherThreadPool() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "Thread: " + Thread.currentThread().getName();
        }, executorService);

        System.out.println(future.get());
        System.out.println("Thread: " + Thread.currentThread().getName());

        executorService.shutdown();
    }

}
