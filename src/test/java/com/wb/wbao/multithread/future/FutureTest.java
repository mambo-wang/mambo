package com.wb.wbao.multithread.future;

import com.wb.wbao.common.concurrent.MamboExecutors;

import java.util.concurrent.*;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService service = MamboExecutors.get().getMamboService();

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> calc(60), service)
                .thenCompose((i) -> CompletableFuture.supplyAsync(() -> calc(i)))
                .exceptionally(ex -> {
                    System.out.println(ex.toString());
                    return 0;
                })
                .thenApply(str -> "\"" + str + "\"")
                .thenAccept(System.out::println);

        System.out.println("one");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("two");
        future.get(3, TimeUnit.SECONDS);


    }

    private static Integer calc(Integer param) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("calc" + param + "depart");

        return param/2;
    }
}
