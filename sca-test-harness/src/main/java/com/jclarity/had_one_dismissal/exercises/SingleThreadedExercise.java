package com.jclarity.had_one_dismissal.exercises;

import static java.lang.Integer.parseInt;

import com.jclarity.had_one_dismissal.Exercise;
import com.jclarity.had_one_dismissal.api.CreateCompanyAndJob;

public class SingleThreadedExercise extends Exercise {

    public SingleThreadedExercise(String threadCount) {
        super(parseInt(threadCount));
    }

    @Override
    public void runExercise() {
        for (int i = 0; i < threadPool.getCorePoolSize(); i++) {
            threadPool.execute(new CreateCompanyAndJob(this));
        }
    }

    @Override
    public void init() {
        // Don't actually cause a deadlock
        // just use its additional blocking to force things to be single threaded
        performanceProblemsMXBean.setDeadlockEnabled(true);
    }

    @Override
    public void reset() {
        performanceProblemsMXBean.setDeadlockEnabled(false);
    }

}
