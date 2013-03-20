package com.jclarity.had_one_dismissal.form;

public class SearchForm {

    private String location;
    private String jobTitle;
    private String keywords;
    private String salaryRange;

    public SearchForm() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    @Override
    public String toString() {
        return "SearchForm [location=" + location + ", jobTitle=" + jobTitle + ", keywords=" + keywords + ", salaryRange=" + salaryRange + "]";
    }

}
