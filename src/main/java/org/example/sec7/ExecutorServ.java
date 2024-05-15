package org.example.sec7;

import org.example.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServ {
    private static final Logger log = LoggerFactory.getLogger(ExecutorServ.class);

    public static void main(String[] args) {
        /**
         * single thread executes all the tasks sequentially
         */
        //execute(Executors.newSingleThreadExecutor(), 3);

        /**
         * threadpool with 1 thread doesn't have any difference with newSingleThreadExecutor
         * threadpool with 5 threads will create 5 thread
         */
        //execute(Executors.newFixedThreadPool(5), 10);

        /**
         * create thread pool with 0 threads. based on no of task it will create threads
         */
        //execute(Executors.newCachedThreadPool(), 10);

        /**
         * ThreadPerTaskExecutor create thread per task
         * Here virtual thread task executor internally return threadPerTaskExecutor
         */
        //execute(Executors.newVirtualThreadPerTaskExecutor(), 10);

        schedule();
    }

    public static void schedule() {
        try (var executor = Executors.newSingleThreadScheduledExecutor()) {
            executor.scheduleAtFixedRate(() -> {
                log.info("executing task");
            }, 0, 1, TimeUnit.SECONDS);
        }
    }

    public static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 0; i < taskCount; i++) {
                int j = i;
                executorService.submit(() -> ioTask(j));
            }
            log.info("submitted");
        }
    }

    public static void ioTask(int i) {
        log.info("Task started {} thread info: {}", i, Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(5));
        log.info("Task ended {} thread info: {}", i, Thread.currentThread());
    }


}
