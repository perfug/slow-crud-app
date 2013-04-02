package com.jclarity.had_one_dismissal.domain;

public class CompanyAndJob {

    private final Company company;
    private final JobListing job;

    public CompanyAndJob() {
        company = new Company();
        job = new JobListing();
    }

    public Company getCompany() {
        return company;
    }

    public JobListing getJob() {
        return job;
    }

}
