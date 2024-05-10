package org.example.sec5;

import org.example.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;

public class IOTask {
    private static final Logger log = LoggerFactory.getLogger(IOTask.class);
    private static final ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        //Runnable runnable = () -> log.info("new thread");
        demo(Thread.ofVirtual().name("VT ", 1));
        //Thread.ofVirtual().start(runnable);
//            demo(Thread.ofPlatform().name("PT ", 1));

        CommonUtils.sleep(Duration.ofSeconds(150));
    }

    public static void demo(Thread.Builder thread) {
        for (int i = 1; i <= 10; i++) {
            thread.start(() -> {
                //log.info("Thread starting {}", Thread.currentThread());
                logFun();
                log.info("Thread loop *********** ending {}", Thread.currentThread());
            });
        }
    }

    public static synchronized void logFun() {
        log.info("virtual thread {} entering", Thread.currentThread());
        list.add(1);
        CommonUtils.sleep(Duration.ofSeconds(2));
        log.info("virtual thread {} ending", Thread.currentThread());
    }
}
