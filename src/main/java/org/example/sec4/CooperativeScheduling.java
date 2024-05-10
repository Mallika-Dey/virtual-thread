package org.example.sec4;

import org.example.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class CooperativeScheduling {
    private static final Logger logger = LoggerFactory.getLogger(CooperativeScheduling.class);

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    public static void main(String[] args) {
        var builder = Thread.ofVirtual();
        var thread1 = builder.unstarted(() -> demo(1));
        var thread2 = builder.unstarted(() -> demo(2));

        thread1.start();
        thread2.start();

        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    public static void demo(int threadNumber) {
        logger.info("thread {} starting", threadNumber);
        for (int i = 1; i < 10; i++) {
            logger.info("thread {} is printing {}, thread number {}", threadNumber, i, Thread.currentThread());
            if (i % 3 == 0) Thread.yield();
        }
        logger.info("thread {} ending", threadNumber);
    }
}
