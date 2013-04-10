package com.jclarity.had_one_dismissal.api;

import com.jclarity.had_one_dismissal.Exercise;

public abstract class Job implements Runnable {

    protected HadOneDismissalApi hadOneDismissal = new HadOneDismissalApi();

    protected final Exercise exercise;

    public Job(Exercise exercise) {
        this.exercise = exercise;
    }

    @Override
    public void run() {
        while(exercise.isRunning()) {
            try {
                runJob();
            } catch (Exception e) {
                // Deliberatly ignored
            }
        }
    }

    protected abstract void runJob() throws Exception;

}
