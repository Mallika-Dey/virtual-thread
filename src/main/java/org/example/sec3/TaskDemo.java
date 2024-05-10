package org.example.sec3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class TaskDemo {
    private static final Logger log = LoggerFactory.getLogger(TaskDemo.class);
    private static final int TASK_COUNT = 3 * Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        log.info("Task count {}", TASK_COUNT);
        //demo(Thread.ofPlatform().name("PT", 1));
        demo(Thread.ofVirtual().name("VT", 1));
    }

    public static void demo(Thread.Builder builder) {
        var latch = new CountDownLatch(TASK_COUNT);
        for (int i = 1; i <= TASK_COUNT; i++) {
            builder.start(() -> {
                Task.cpuIntensive(40);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
