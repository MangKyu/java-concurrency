package com.mangkyu.concurrency.java5;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executor;

class ExecutorTest {

    @Test
    void executorRun() {
        final Runnable runnable = () -> System.out.println("Thread: " + Thread.currentThread().getName());

        Executor executor = new RunExecutor();
        executor.execute(runnable);
    }

    static class RunExecutor implements Executor {

        @Override
        public void execute(final Runnable command) {
            command.run();
        }
    }

    @Test
    void executorStart() {
        final Runnable runnable = () -> System.out.println("Thread: " + Thread.currentThread().getName());

        Executor executor = new StartExecutor();
        executor.execute(runnable);
    }

    static class StartExecutor implements Executor {

        @Override
        public void execute(final Runnable command) {
            new Thread(command).start();
        }
    }

}
