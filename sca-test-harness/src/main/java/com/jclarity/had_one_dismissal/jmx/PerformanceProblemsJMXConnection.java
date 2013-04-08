package com.jclarity.had_one_dismissal.jmx;

import com.jclarity.crud_common.api.PerformanceProblemsMXBean;

public class PerformanceProblemsJMXConnection extends JMXConnection {

    private static final int JMX_HAD_ONE_DISMISSAL_SERVER_PORT = Integer.parseInt(System.getProperty("JMX_HOD_SERVER_PORT", "1099"));

    public PerformanceProblemsJMXConnection() {
        super(JMX_SERVER_HOST, JMX_HAD_ONE_DISMISSAL_SERVER_PORT);
    }

    public PerformanceProblemsMXBean getPerformanceProblemsMXBean() throws JMXConnectionError {
        return getBean(PerformanceProblemsMXBean.ADDRESS, PerformanceProblemsMXBean.class);
    }

}
