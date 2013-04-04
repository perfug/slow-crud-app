package com.jclarity.had_one_dismissal.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class SwitchableDataSource extends AbstractRoutingDataSource {

    @Autowired private PerformanceProblems problems;

    @Override
    protected Object determineCurrentLookupKey() {
        return problems.getDatabaseType();
    }

}
