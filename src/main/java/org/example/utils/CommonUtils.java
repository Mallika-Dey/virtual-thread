package org.example.utils;

import java.time.Duration;

public class CommonUtils {
    public static void sleep(Duration i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static long timer(Runnable runnable) {
        var start = System.currentTimeMillis();
        runnable.run();
        var end = System.currentTimeMillis();
        return end - start;
    }
}
