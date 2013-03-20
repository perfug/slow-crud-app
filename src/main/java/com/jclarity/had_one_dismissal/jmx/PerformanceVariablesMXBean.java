package com.jclarity.had_one_dismissal.jmx;

public interface PerformanceVariablesMXBean {

    public static final String ADDRESS = "com.jclarity.had_one_dismissal:type=PerformanceVariables";

    public void setSleepTime(int length);

    public int getSleepTime();

    public int getThreadPoolSize();

    public void setThreadPoolSize(int size);

}
