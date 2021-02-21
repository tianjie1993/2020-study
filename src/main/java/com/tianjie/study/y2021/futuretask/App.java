package com.tianjie.study.y2021.futuretask;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //可以利用get 阻塞任务 异步完成task返回
//        CompletableFuture<Integer> task1= new CompletableFuture<>();
//        task1.complete(2);
//        System.out.println(task1.get());
        //自己实现一个简单的任务
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        SyncTask<String> task = new SyncTask<>();
        executorService.submit(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成任务");
            task.complete("11111");
        });
        System.out.println("等待任务完成");
        String msg = task.get();
        System.out.println("接收到信息"+msg);

    }
}
