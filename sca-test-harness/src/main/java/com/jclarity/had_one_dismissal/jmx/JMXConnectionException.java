package com.jclarity.had_one_dismissal.jmx;


public class JMXConnectionException extends Exception {

    public JMXConnectionException(String name, Exception e) {
        super("Error connecting to "+name,e);
    }

}
