package org.example.sec1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void threadRun(int i) {
        log.info("thread {} starting", i);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("thread {} ending", i);

    }
}
