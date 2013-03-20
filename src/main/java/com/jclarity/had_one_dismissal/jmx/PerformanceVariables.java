package com.jclarity.had_one_dismissal.jmx;

import org.springframework.stereotype.Component;

@Component
public class PerformanceVariables extends JMXComponent implements PerformanceVariablesMXBean {

    private int sleepTime;
    private int threadPoolSize;

    public PerformanceVariables() throws Exception {
        register(PerformanceVariablesMXBean.ADDRESS);
    }

    @Override
    public void setSleepTime(int length) {
        this.sleepTime = length;
    }

    @Override
    public int getSleepTime() {
        return sleepTime;
    }

    @Override
    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    @Override
    public void setThreadPoolSize(int size) {
        threadPoolSize = size;
    }

}
