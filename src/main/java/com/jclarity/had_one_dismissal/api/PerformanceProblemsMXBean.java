package com.jclarity.had_one_dismissal.api;

public interface PerformanceProblemsMXBean {

    public boolean isEnabled(Problem problem);

    public void enable(Problem problem);

    public void disable(Problem problem);

}
