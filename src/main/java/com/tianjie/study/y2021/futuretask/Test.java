package com.tianjie.study.y2021.futuretask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task = CompletableFuture.supplyAsync(()->{
            return 1;
        });
        task.complete(2);
    }
}
