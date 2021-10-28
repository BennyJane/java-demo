package benny.jane.com.base;

import java.util.concurrent.*;

public class CreateThreadPoolExecutor extends Task {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(
                2, 10, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10, true),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        CreateThreadPoolExecutor threadPoolExecutor = new CreateThreadPoolExecutor();
        threadPoolExecutor.runTask(executorService, 10, 2);
        executorService.shutdown();
    }
}
