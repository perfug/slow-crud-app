package com.jclarity.had_one_dismissal.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class SwitchableDataSource extends AbstractRoutingDataSource {

    @Autowired private PerformanceProblems problems;

    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("DB = " + problems.getDatabaseType());
        return problems.getDatabaseType();
    }

}
