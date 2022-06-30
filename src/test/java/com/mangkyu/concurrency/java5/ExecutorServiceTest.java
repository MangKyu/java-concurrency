package com.mangkyu.concurrency.java5;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExecutorServiceTest {

    @Test
    void shutdown() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable runnable = () -> System.out.println("Thread: " + Thread.currentThread().getName());
        executorService.execute(runnable);

        executorService.shutdown();

        RejectedExecutionException result = assertThrows(RejectedExecutionException.class, () -> executorService.execute(runnable));
        assertThat(result).isInstanceOf(RejectedExecutionException.class);
    }

    @Test
    void shutdownNow() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Start");
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            System.out.println("End");
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(runnable);

        executorService.shutdownNow();
        Thread.sleep(1000L);
    }

    @Test
    void invokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Instant start = Instant.now();

        Callable<String> hello = () -> {
            Thread.sleep(1000L);
            final String result = "Hello";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> mang = () -> {
            Thread.sleep(2000L);
            final String result = "Mang";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> kyu = () -> {
            Thread.sleep(3000L);
            final String result = "kyu";
            System.out.println("result = " + result);
            return result;
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, mang, kyu));
        for (Future<String> f : futures) {
            System.out.println(f.get());
        }

        System.out.println("time = " + Duration.between(start, Instant.now()).getSeconds());
        executorService.shutdown();
    }

    @Test
    void invokeAny() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Instant start = Instant.now();

        Callable<String> hello = () -> {
            Thread.sleep(1000L);
            final String result = "Hello";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> mang = () -> {
            Thread.sleep(2000L);
            final String result = "Mang";
            System.out.println("result = " + result);
            return result;
        };

        Callable<String> kyu = () -> {
            Thread.sleep(3000L);
            final String result = "kyu";
            System.out.println("result = " + result);
            return result;
        };

        String result = executorService.invokeAny(Arrays.asList(hello, mang, kyu));
        System.out.println("result = " + result + " time = " + Duration.between(start, Instant.now()).getSeconds());

        executorService.shutdown();
    }

}
