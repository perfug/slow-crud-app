package com.jclarity.auth.service;

import org.springframework.stereotype.Component;

import com.jclarity.crud_common.api.AuthServicePerformanceVariablesMXBean;
import com.jclarity.crud_common.jmx.JMXComponent;

@Component
public class AuthServicePerformanceVariables extends JMXComponent implements AuthServicePerformanceVariablesMXBean {

    private int sleepTime;

    public AuthServicePerformanceVariables() throws Exception {
        sleepTime = 0;
        register(ADDRESS);
    }

    @Override
    public void setSleepTime(int length) {
        this.sleepTime = length;
    }

    @Override
    public int getSleepTime() {
        return sleepTime;
    }

}
