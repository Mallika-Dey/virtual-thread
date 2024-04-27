package org.example.sec2;

import org.example.utils.CommonUtils;

import java.time.Duration;

public class ThreadRunner {
    public static void main(String[] args) {
        //demo(Thread.ofPlatform().name("pt", 1));
        demo(Thread.ofVirtual().name("vt", 1));
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    public static void demo(Thread.Builder builder) {
        for (int i = 1; i < 21; i++) {
            int j = i;
            builder.start(() -> Task.execute(j));
        }
    }
}
