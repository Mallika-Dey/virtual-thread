package org.example.sec3;

import org.example.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {
    private static final Logger log = LoggerFactory.getLogger(org.example.sec2.Task.class);

    public static void cpuIntensive(int i) {
        log.info("starting cpu intensive task. Thread info {}", Thread.currentThread());
        var timeTaken = CommonUtils.timer(() -> findFiv(i));
        log.info("ending task. time taken {} ms", timeTaken);
    }

    public static long findFiv(long i) {
        if (i < 2) return i;
        return findFiv(i - 1) + findFiv(i - 2);
    }
}
