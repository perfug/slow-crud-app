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
import com.jclarity.had_one_dismissal.HadOneDismissalApi;

public abstract class JMXConnection implements Closeable {
    protected static final String JMX_SERVER_HOST = HadOneDismissalApi.HOST;

    private final String name;
    private final String host;
    private final int port;

    private JMXConnector cachedConnector;

    public JMXConnection(String host, int port) {
        this.host = host;
        this.port = port;
        this.name = host + ":" + port;
    }

    protected <BeanType> BeanType getBean(String mxbeanName, Class<BeanType> clazz) throws JMXConnectionError {
        try {
            MBeanServerConnection connection = connect();
            return JMX.newMXBeanProxy(connection, new ObjectName(mxbeanName), clazz);
        } catch (MalformedObjectNameException e) {
            throw new JMXConnectionError(name, e);
        }
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
