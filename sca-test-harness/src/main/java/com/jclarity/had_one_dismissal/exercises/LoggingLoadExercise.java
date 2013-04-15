package com.jclarity.had_one_dismissal.exercises;

public class LoggingLoadExercise extends PopulateExercise {

    public LoggingLoadExercise(String threadCount) {
        super(threadCount);
    }

    @Override
    public void init() {
        performanceProblems.setSavingDBQueries(false);
        performanceProblems.setRootLoggingLevel("ALL");
    }

    @Override
    public void reset() {
        performanceProblems.setSavingDBQueries(true);
        performanceProblems.setRootLoggingLevel("ERROR");
    }

}
