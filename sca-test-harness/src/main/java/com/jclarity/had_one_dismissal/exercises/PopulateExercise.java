package com.jclarity.had_one_dismissal.exercises;

import static java.lang.Integer.parseInt;

import com.jclarity.had_one_dismissal.Exercise;
import com.jclarity.had_one_dismissal.api.Job;

public abstract class PopulateExercise extends Exercise {

    class PopulateDb extends Job {
        public PopulateDb() {
            super(PopulateExercise.this);
        }

        @Override
        protected void runJob() throws Exception {
            hadOneDismissal.populateDb();
        }
    }

    public PopulateExercise(String threadCount) {
        super(parseInt(threadCount));
    }

    @Override
    public void runExercise() {
        isRunning = true;
        for (int i = 0; i < poolSize; i++) {
            threadPool.execute(new PopulateDb());
        }
    }

}
