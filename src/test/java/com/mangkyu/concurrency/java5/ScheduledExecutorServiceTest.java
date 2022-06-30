package com.mangkyu.concurrency.java5;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class ScheduledExecutorServiceTest {

    @Test
    void schedule() throws InterruptedException {
        Runnable runnable = () -> System.out.println("Thread: " + Thread.currentThread().getName());
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(runnable, 1, TimeUnit.SECONDS);

        Thread.sleep(2000L);
        executorService.shutdown();
    }

    @Test
    void scheduleAtFixedRate() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Start scheduleAtFixedRate:    " + LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finish scheduleAtFixedRate:    " + LocalTime.now());
        };
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(runnable, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(10000L);
        executorService.shutdown();
    }


    @Test
    void scheduleWithFixedDelay() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("Start scheduleWithFixedDelay:    " + LocalTime.now());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finish scheduleWithFixedDelay:    " + LocalTime.now());
        };

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(runnable, 0, 2, TimeUnit.SECONDS);

        Thread.sleep(10000L);
        executorService.shutdown();
    }


}
