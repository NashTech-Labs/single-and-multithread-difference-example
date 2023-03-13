package com.knoldus;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TaskThroughput {
    public static void main(String[] args) {

        singleThread();
        multiThread();

    }

    private static void singleThread() {
        try (var executor = Executors.newSingleThreadExecutor()) {
            IntStream.range(0, 100_000).forEach(i -> executor.submit(
                    () -> {
                        Thread.sleep(Duration.ofSeconds(1));
                        System.out.println(i);
                        return i;
                    }));
        }
    }

    private static void multiThread() {
        try (var executor = Executors.newThreadPerTaskExecutor(Executors.defaultThreadFactory())) {
            IntStream.range(0, 100_000).forEach(i -> executor.submit(() -> {
                Thread.sleep(Duration.ofSeconds(1));
                System.out.println(i);
                return i;
            }));
        }
    }
}



