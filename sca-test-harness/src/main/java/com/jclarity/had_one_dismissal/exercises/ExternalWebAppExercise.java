package com.jclarity.had_one_dismissal.exercises;

import static java.lang.Integer.parseInt;

import com.jclarity.had_one_dismissal.Exercise;
import com.jclarity.had_one_dismissal.api.Job;

public class ExternalWebAppExercise extends Exercise {

    class LoginAndOut extends Job {

        public LoginAndOut() {
            super(ExternalWebAppExercise.this);
        }

        @Override
        protected void runJob() throws Exception {
            hadOneDismissal.login("foo", "bar");
            hadOneDismissal.logout();
        }
    }

    private final int sleepTime;

    public ExternalWebAppExercise(String sleepTime) {
        this.sleepTime = parseInt(sleepTime);
    }

    @Override
    public void runExercise() {
        isRunning = true;
        for (int i = 0; i < poolSize; i++) {
            threadPool.execute(new LoginAndOut());
        }
    }

    @Override
    public void init() {
        authServicePerformanceVariables.setSleepTime(sleepTime);
    }

    @Override
    public void reset() {
        authServicePerformanceVariables.setSleepTime(0);
    }

}
