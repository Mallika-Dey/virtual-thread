package org.example.sec2;

import org.example.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;


public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void execute(int i) {
        log.info("starting thread {}", i);
        try {
            method1(i);
        } catch (Exception e) {
            log.error("error for {}", i, e);
        }
        log.info("ending thread {}", i);
    }

    public static void method1(int i) {
        CommonUtil.sleep(Duration.ofMillis(100));
        try {
            method2(i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void method2(int i) {
        CommonUtil.sleep(Duration.ofMillis(100));
        method3(i);
    }

    public static void method3(int i) {
        CommonUtil.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("i can't be 4");
        }
    }
}
