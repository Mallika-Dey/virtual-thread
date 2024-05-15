package org.example.sec5;

import org.example.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockClass {
    private static final Logger log = LoggerFactory.getLogger(ReentrantLockClass.class);
    private static final ReentrantLock lock = new ReentrantLock(true);
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
                log.info("Thread starting {}", Thread.currentThread());
                //inMemoryTask();
                ioTask();
                //log.info("Thread loop *********** ending {}", Thread.currentThread());
            });
        }
    }

    public static void inMemoryTask() {
        try {
            log.info("before locking {}", Thread.currentThread());
            lock.lock();
            log.info("virtual thread {} entering", Thread.currentThread());
            list.add(1);
            log.info("virtual thread {} ending", Thread.currentThread());
        } catch (Exception ex) {
            log.error("error", ex);
        } finally {
            lock.unlock();
        }
    }

    public static void ioTask() {
        try {
            log.info("before locking {}", Thread.currentThread());
            lock.lock();
            log.info("virtual thread {} entering hold count {}", Thread.currentThread(), lock.getHoldCount());
            CommonUtils.sleep(Duration.ofSeconds(10));
            log.info("virtual thread {} ending", Thread.currentThread());
        } catch (Exception ex) {
            log.error("error", ex);
        } finally {
            lock.unlock();
        }
    }
}
