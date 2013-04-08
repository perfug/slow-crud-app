package com.jclarity.had_one_dismissal;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import com.jclarity.crud_common.api.PerformanceProblemsMXBean;
import com.jclarity.had_one_dismissal.jmx.PerformanceProblemsJMXConnection;

public abstract class Exercise {

    protected final ScheduledThreadPoolExecutor threadPool;
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    protected volatile boolean isRunning = true;
    protected PerformanceProblemsMXBean performanceProblemsMXBean;
    private PerformanceProblemsJMXConnection jmxConnection;

    public static void runExercise(String clazzName, long timeLimitInMs) {
        try {
            @SuppressWarnings("unchecked")
            Class<? extends Exercise> type = (Class<? extends Exercise>) Class.forName(clazzName);
            if (type != null) {
                Exercise exercise = type.newInstance();
                exercise.runExercise(timeLimitInMs);
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException("failed to load exercise", e);
        }
    }

    public Exercise() {
        threadPool = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
        jmxConnection = new PerformanceProblemsJMXConnection();
    }

    protected void stop() {
        reset();
        isRunning = false;
        threadPool.shutdownNow();
        jmxConnection.close();
    }

    public void runExercise(long timeLimitInMs) {
        init();
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

    public void init() {
    };

    public void reset() {
    };
}
