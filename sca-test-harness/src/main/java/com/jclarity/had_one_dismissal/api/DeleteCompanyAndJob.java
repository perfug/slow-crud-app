package com.jclarity.had_one_dismissal.api;

import com.jclarity.had_one_dismissal.Exercise;

public class DeleteCompanyAndJob extends Job {

    public DeleteCompanyAndJob(Exercise exercise) {
        super(exercise);
    }

    @Override
    protected void runJob() throws Exception {
        // a race condition here that can lead to a record failing to be deleted
        // not a big issue
        if (Ids.companyDeletedCount.intValue() < Ids.companyCreatedCount.intValue()) {
            hadOneDismissal.deleteCompanyAndJobById(Ids.companyDeletedCount.incrementAndGet() + 1);
        } else {
            Thread.sleep(100);
        }
    }
}