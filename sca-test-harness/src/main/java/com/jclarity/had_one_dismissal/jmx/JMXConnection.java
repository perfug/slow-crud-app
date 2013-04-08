package com.jclarity.had_one_dismissal.jmx;

import java.io.Closeable;
import java.io.IOException;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.google.common.io.Closeables;
import com.jclarity.crud_common.api.PerformanceProblemsMXBean;
import com.jclarity.had_one_dismissal.HadOneDismissalApi;

public class JMXConnection implements Closeable {
    private static final String MXBEAN_NAME = "com.jclarity.had_one_dismissal:type=PerformanceProblems";

    private static final String JMX_SERVER_HOST = HadOneDismissalApi.HOST;
    private static final int JMX_SERVER_PORT = Integer.parseInt(System.getProperty("JMX_SERVER_PORT", "1099"));

    private final String name;
    private final String host;
    private final int port;

    private JMXConnector cachedConnector;
    private PerformanceProblemsMXBean mxbean;

    public JMXConnection() {
        this(JMX_SERVER_HOST, JMX_SERVER_PORT);
    }

    public JMXConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.name = host + ":" + port;
    }

    public PerformanceProblemsMXBean getPerformanceProblemBean() throws JMXConnectionError {
        if (mxbean == null) {
            try {
                MBeanServerConnection connection = connect();
                mxbean = JMX.newMXBeanProxy(connection, new ObjectName(MXBEAN_NAME), PerformanceProblemsMXBean.class);
            } catch (MalformedObjectNameException e) {
                throw new JMXConnectionError(name, e);
            }
        }
        return mxbean;
    }

    private MBeanServerConnection connect() throws JMXConnectionError {
        if (cachedConnector != null) {
            try {
                return cachedConnector.getMBeanServerConnection();
            } catch (IOException e) {
                throw new JMXConnectionError(name, e);
            }
        }

        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi");
            JMXConnector connector = JMXConnectorFactory.connect(url);
            connector.connect();
            this.cachedConnector = connector;
            return cachedConnector.getMBeanServerConnection();
        } catch (IOException e) {
            throw new JMXConnectionError(name, e);
        }
    }

    @Override
    public void close() {
        Closeables.closeQuietly(cachedConnector);
    }
}
