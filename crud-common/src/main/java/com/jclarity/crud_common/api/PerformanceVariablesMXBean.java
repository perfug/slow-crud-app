package com.jclarity.crud_common.api;

public interface PerformanceVariablesMXBean {

    public static final String ADDRESS = "com.jclarity.had_one_dismissal:type=PerformanceVariables";

    public int getThreadPoolSize();

    public void setThreadPoolSize(int size);

}
