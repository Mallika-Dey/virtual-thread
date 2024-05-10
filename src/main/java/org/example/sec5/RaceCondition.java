package org.example.sec5;

import org.example.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;

//1st task
public class RaceCondition {
    private static final Logger log = LoggerFactory.getLogger(RaceCondition.class);
    private static final ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        demo(Thread.ofVirtual().name("VT", 1));
        //demo(Thread.ofPlatform().name("PT"));
        CommonUtils.sleep(Duration.ofSeconds(20));
        log.info("list size {}", list.size());
    }

    public static void demo(Thread.Builder thread) {
        for (int i = 0; i < 50; i++) {
            thread.start(() -> {
                log.info("Thread starting {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    logFun();
                }
                log.info("Thread ending {}", Thread.currentThread());
            });
        }
    }

    /*public static void logFun() {
        list.add(1);
    }*/

    public static synchronized void logFun() {
        list.add(1);
    }
}
