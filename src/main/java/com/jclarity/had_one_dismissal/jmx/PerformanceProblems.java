package com.jclarity.had_one_dismissal.jmx;

import org.springframework.stereotype.Component;

@Component
public class PerformanceProblems extends JMXComponent implements PerformanceProblemsMXBean {

    private boolean deadlockEnabled;
    private boolean callingRestService;
    private boolean batchingDBQueries;

    public PerformanceProblems() throws Exception {
        register(PerformanceProblemsMXBean.ADDRESS);
    }

    @Override
    public boolean isDeadlockEnabled() {
        return deadlockEnabled;
    }

    @Override
    public void setDeadlockEnabled(boolean enabled) {
        deadlockEnabled = enabled;
    }

    @Override
    public boolean isCallingRestService() {
        return callingRestService;
    }

    @Override
    public void setCallingRestService(boolean calling) {
        callingRestService = calling;
    }

    @Override
    public boolean isBatchingDBQueries() {
        return batchingDBQueries;
    }

    @Override
    public void setBatchingDBQueries(boolean batching) {
        batchingDBQueries = batching;
    }

}
