package org.example.sec1;

import java.util.concurrent.CountDownLatch;

public class Main {
    private static final int Max_Thread = 10;
    private static final int Max_Virtual_Thread = 10_000;

    public static void main(String[] args) {
        //platFormThread();
//        platFromThreadBuilder();
        //daemonThread();
        virtualThread();
    }

    public static void platFormThread() {
        for (int i = 0; i < Max_Thread; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.threadRun(j));
            thread.start();
        }
    }

    public static void platFromThreadBuilder() {
        Thread.Builder.OfPlatform builder = Thread.ofPlatform().name("platform", 1);
        for (int i = 0; i < Max_Thread; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.threadRun(j));
            thread.start();
        }
    }

    public static void daemonThread() {
        //count down latch for making main thread wait
        CountDownLatch countDownLatch = new CountDownLatch(Max_Thread);
        //platform daemon thread.
        Thread.Builder.OfPlatform builder = Thread.ofPlatform().daemon().name("daemon", 1);
        for (int i = 0; i < Max_Thread; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.threadRun(j);
                countDownLatch.countDown();
            });
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void virtualThread() {
        CountDownLatch countDownLatch = new CountDownLatch(Max_Virtual_Thread);
        Thread.Builder builder = Thread.ofVirtual().name("virtual", 1);
        for (int i = 0; i < Max_Virtual_Thread; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.threadRun(j);
                countDownLatch.countDown();
            });
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}