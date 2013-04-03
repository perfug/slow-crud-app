package com.jclarity.had_one_dismissal.jmx;

import org.springframework.stereotype.Component;

import com.jclarity.crud_common.api.PerformanceVariablesMXBean;
import com.jclarity.crud_common.jmx.JMXComponent;

@Component
public class PerformanceVariables extends JMXComponent implements PerformanceVariablesMXBean {

    private int threadPoolSize;

    public PerformanceVariables() throws Exception {
        register(PerformanceVariablesMXBean.ADDRESS);
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
