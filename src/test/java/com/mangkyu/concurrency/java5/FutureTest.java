package com.mangkyu.concurrency.java5;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;

class FutureTest {

    @Test
    void get() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = callable();

        // It takes 3 seconds by blocking(블로킹에 의해 3초 걸림)
        Future<String> future = executorService.submit(callable);

        System.out.println(future.get());

        executorService.shutdown();
    }

    @Test
    void isCancelled_False() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = callable();

        Future<String> future = executorService.submit(callable);
        assertThat(future.isCancelled()).isFalse();

        executorService.shutdown();
    }

    @Test
    void isCancelled_True() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = callable();

        Future<String> future = executorService.submit(callable);
        future.cancel(true);

        assertThat(future.isCancelled()).isTrue();
        executorService.shutdown();
    }

    @Test
    void isDone_False() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = callable();

        Future<String> future = executorService.submit(callable);

        assertThat(future.isDone()).isFalse();
        executorService.shutdown();
    }

    @Test
    void isDone_True() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = callable();

        Future<String> future = executorService.submit(callable);

        while (future.isDone()) {
            assertThat(future.isDone()).isTrue();
            executorService.shutdown();
        }

    }

    private Callable<String> callable() {
        return new Callable<String>() {
            @Override
            public String call() throws InterruptedException {
                Thread.sleep(3000L);
                return "Thread: " + Thread.currentThread().getName();
            }
        };
    }

}
