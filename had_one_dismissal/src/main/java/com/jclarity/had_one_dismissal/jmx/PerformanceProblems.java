package com.jclarity.had_one_dismissal.jmx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import com.jclarity.crud_common.api.Database;
import com.jclarity.crud_common.api.PerformanceProblemsMXBean;
import com.jclarity.crud_common.jmx.JMXComponent;

@Component
public class PerformanceProblems extends JMXComponent implements PerformanceProblemsMXBean {

    @Autowired private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    private boolean deadlockEnabled;
    private boolean callingRestService;
    private boolean batchingDBQueries;
    private Database database;

    public PerformanceProblems() throws Exception {
        register(PerformanceProblemsMXBean.ADDRESS);
        database = Database.IN_MEMORY;
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

    @Override
    public Database getDatabaseType() {
        return database;
    }

    @Override
    public void setDatabaseType(Database database) {
        this.database = database;
        entityManagerFactory.afterPropertiesSet();
    }

}
