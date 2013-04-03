package com.jclarity.had_one_dismissal.web;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jclarity.had_one_dismissal.domain.Company;
import com.jclarity.had_one_dismissal.domain.CompanyAndJob;
import com.jclarity.had_one_dismissal.domain.JobListing;

// Naively avoid database concurrency issues by aggressively locking
// Potential deadlock between the delete and save methods which perform
// lock acquisition/release in a different order
@RequestMapping("/companyandjob/**")
@Controller
public class CompanyAndJobController {

    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value="{jobId}/", method = RequestMethod.DELETE)
    public String delete(@PathVariable("jobId") Long jobId, Model uiModel) {
        JobListing.LOCK.lock();
        try {
            Company.LOCK.lock();
            try {
                JobListing job = JobListing.findJobListing(jobId);
                Company company = job.getCompany();
                company.remove();
                job.remove();
            } finally {
                Company.LOCK.unlock();
            }
        } finally {
            JobListing.LOCK.unlock();
        }
        return index(uiModel);
    }

    @RequestMapping(method = RequestMethod.POST)
    public synchronized String save(CompanyAndJob object, Model uiModel) {
        Company company = object.getCompany();
        JobListing job = object.getJob();

        Company.LOCK.lock();
        try {
            JobListing.LOCK.lock();
            try {
                company.persist();
                job.persist();
            } finally {
                JobListing.LOCK.unlock();
            }
        } finally {
            Company.LOCK.unlock();
        }
        return index(uiModel);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel) {
        uiModel.addAttribute("object", new CompanyAndJob());
        return "companyandjob/index";
    }

}
