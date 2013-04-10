package com.jclarity.had_one_dismissal.api;

import com.jclarity.had_one_dismissal.Exercise;

public class CreateCompanyAndJob extends Job {

    public CreateCompanyAndJob(Exercise exercise) {
        super(exercise);
    }

    @Override
    protected void runJob() throws Exception {
        hadOneDismissal.createCompanyAndJob("wang", "head of awesome", 3, 4, "sdfettre");
        Ids.companyCreatedCount.incrementAndGet();
    }

}
