package com.jclarity.had_one_dismissal;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public abstract class Exercise {

    protected final ScheduledThreadPoolExecutor threadPool;
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    protected volatile boolean isRunning = true;

    public static void runExercise(String clazzName, long timeLimitInMs) {
        try {
            @SuppressWarnings("unchecked")
            Class<? extends Exercise> type = (Class<? extends Exercise>) Class.forName(clazzName);
            if (type != null) {
                Exercise exercise = type.newInstance();
                exercise.runExercise(timeLimitInMs);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Exercise() {
        threadPool = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
    }

    protected void stop() {
        isRunning = false;
        threadPool.shutdownNow();
    }

    public void runExercise(long timeLimitInMs) {
        runExercise();
        long end = System.currentTimeMillis() + timeLimitInMs;
        while (isRunning && System.currentTimeMillis() < end) {
            try {
                Thread.sleep(end - System.currentTimeMillis());
            } catch (InterruptedException e) {
                // Deliberately ignore
            }
        }
        stop();
    }

    public abstract void runExercise();
}
