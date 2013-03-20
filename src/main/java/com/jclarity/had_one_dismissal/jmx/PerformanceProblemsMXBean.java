package com.jclarity.had_one_dismissal.jmx;

public interface PerformanceProblemsMXBean {

    public static final String ADDRESS = "com.jclarity.had_one_dismissal:type=PerformanceProblems";

    public boolean isDeadlockEnabled();

    public void setDeadlockEnabled(boolean enabled);

    public boolean isCallingRestService();

    public void setCallingRestService(boolean calling);

    public boolean isBatchingDBQueries();

    public void setBatchingDBQueries(boolean batching);

}
