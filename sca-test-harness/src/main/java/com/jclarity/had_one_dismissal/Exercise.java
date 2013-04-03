package com.jclarity.had_one_dismissal;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public abstract class Exercise {

    protected final ScheduledThreadPoolExecutor threadPool;
    private static final int THREAD_POOL_SIZE = 4;

    public Exercise() {
        threadPool = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
    }
}
