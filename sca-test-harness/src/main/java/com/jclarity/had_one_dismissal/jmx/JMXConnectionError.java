package com.jclarity.had_one_dismissal.jmx;


public class JMXConnectionError extends Exception {

    public JMXConnectionError(String name, Exception e) {
        super("Error connecting to "+name,e);
    }

}
