package com.mangkyu.concurrency.java8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

class CompletableFutureComposeTest {

    @Test
    void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });

        // Future 간에 연관 관계가 있는 경우
        CompletableFuture<String> future = hello.thenCompose(this::mangKyu);
        System.out.println(future.get());
    }

    private CompletableFuture<String> mangKyu(String message) {
        return CompletableFuture.supplyAsync(() -> {
            return message + " " + "MangKyu";
        });
    }

    @Test
    void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });

        CompletableFuture<String> mangKyu = CompletableFuture.supplyAsync(() -> {
            return "MangKyu";
        });

        CompletableFuture<String> future = hello.thenCombine(mangKyu, (h, w) -> h + " " + w);
        System.out.println(future.get());
    }

    @Test
    void allOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });

        CompletableFuture<String> mangKyu = CompletableFuture.supplyAsync(() -> {
            return "MangKyu";
        });

        List<CompletableFuture<String>> futures = List.of(hello, mangKyu);

        CompletableFuture<List<String>> result = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))
                .thenApply(v -> futures.stream().
                        map(CompletableFuture::join).
                        collect(Collectors.toList()));

        List<String> stringList = result.get();
        stringList.forEach(System.out::println);

    }

    @Test
    void anyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return "Hello";
        });

        CompletableFuture<String> mangKyu = CompletableFuture.supplyAsync(() -> {
            return "MangKyu";
        });

        CompletableFuture<Void> future = CompletableFuture.anyOf(hello, mangKyu).thenAccept(System.out::println);
        future.get();
    }

}