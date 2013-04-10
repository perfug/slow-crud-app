package com.jclarity.had_one_dismissal.exercises;


public class DeadlockExercise extends CompanyAndJobExercise {

    private static final int ENQUEUE_JOBS_COUNT = 2;
    private static final int DELETE_JOBS_COUNT = 2;

    public DeadlockExercise() {
        super(ENQUEUE_JOBS_COUNT, DELETE_JOBS_COUNT);
    }

    @Override
    public void init() {
        performanceProblems.setDeadlockEnabled(true);
    }

    @Override
    public void reset() {
        performanceProblems.setDeadlockEnabled(false);
    }

}
