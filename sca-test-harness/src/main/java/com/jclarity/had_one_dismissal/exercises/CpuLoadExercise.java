package com.jclarity.had_one_dismissal.exercises;

import static com.jclarity.crud_common.api.Database.EXTERNAL_DAEMON;
import static com.jclarity.crud_common.api.Database.IN_MEMORY;
import static java.lang.Integer.parseInt;

import com.jclarity.had_one_dismissal.Exercise;
import com.jclarity.had_one_dismissal.api.Job;

public class CpuLoadExercise extends Exercise {

    class PopulateDb extends Job {
        public PopulateDb() {
            super(CpuLoadExercise.this);
        }

        @Override
        protected void runJob() throws Exception {
            hadOneDismissal.populateDb();
        }
    }

    public CpuLoadExercise(String threadCount) {
        super(parseInt(threadCount));
    }

    @Override
    public void runExercise() {
        isRunning = true;
        for (int i = 0; i < poolSize; i++) {
            threadPool.execute(new PopulateDb());
        }
    }

    @Override
    public void init() {
        performanceProblems.setDatabaseType(EXTERNAL_DAEMON);
        performanceProblems.setSavingDBQueries(false);
    }

    @Override
    public void reset() {
        performanceProblems.setDatabaseType(IN_MEMORY);
        performanceProblems.setSavingDBQueries(true);
    }

}
