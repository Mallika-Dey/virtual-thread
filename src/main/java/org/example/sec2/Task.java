package org.example.sec2;

import org.example.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void execute(int i) {
        log.info("thread {} starting", i);
        try {
            method1(i);
        } catch (Exception ex) {
            log.error("error for {}", i, ex);
        }
        log.info("thread {} ending", i);
    }

    public static void method1(int i) {
        CommonUtils.sleep(Duration.ofMillis(100));
        try {
            method2(i);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void method2(int i) {
        CommonUtils.sleep(Duration.ofMillis(100));
        method3(i);
    }

    public static void method3(int i) {
        CommonUtils.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("i can't be 4");
        }
    }
}
