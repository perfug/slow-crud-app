package com.jclarity.had_one_dismissal.web;

import static com.google.common.io.Files.readLines;
import static java.nio.charset.Charset.defaultCharset;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jclarity.had_one_dismissal.domain.Applicant;
import com.jclarity.had_one_dismissal.domain.Company;
import com.jclarity.had_one_dismissal.domain.JobListing;
import com.jclarity.had_one_dismissal.domain.Location;
import com.jclarity.had_one_dismissal.domain.Tag;
import com.jclarity.had_one_dismissal.jmx.PerformanceProblems;

@RequestMapping("/populate/**")
@Controller
public class Populate {

    private static final Logger LOGGER = LoggerFactory.getLogger(Populate.class);

    @Autowired private PerformanceProblems problems;

    @Transactional
    @RequestMapping
    public String index() {
        try {
            if (!problems.isBatchingDBQueries()) {
                loadLocations();
                loadTags();
                loadNames();
                loadCompanies();
                loadJobListings();
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "populate/index";
    }

    static void loadJobListings() {
        String description = "some job description";
        List<Tag> tags = Tag.findAllTags();
        List<Company> companys = Company.findAllCompanys();

        for (int i = 0; i < tags.size(); i++) {
            JobListing job = new JobListing();
            job.setCompany(companys.get(i));
            job.setDescription(description);
            job.setPostedAt(new Date());
            int salary = i * 10000;
            job.setSalaryLowerBound(salary - 10);
            job.setSalaryUpperBound(salary + 10);
            job.setTitle(tags.get(i).getName());
            job.persist();
        }
    }

    static void loadCompanies() throws URISyntaxException, IOException {
        List<String> lines = getLines("companies");
        for (Company company : Company.findAllCompanys()) {
            company.remove();
        }

        List<Location> locations = Location.findAllLocations();

        int i = 0;
        for (String line : lines) {
            Company company = new Company();
            Location location = locations.get(i % locations.size());
            company.setLocation(location);
            company.setName(line);
            company.persist();
            i++;
        }
    }

    static void loadNames() throws URISyntaxException, IOException {
        List<String> lines = getLines("names");
        for (Applicant applicant : Applicant.findAllApplicants()) {
            applicant.remove();
        }

        int count = 0;
        for (String line : lines) {
            String[] split = line.split("\t");
            if (split.length != 2)
                continue;

            Applicant applicant = new Applicant();
            applicant.setForename(split[0]);
            applicant.setSurname(split[1]);
            applicant.setYearsExperience(count);
            applicant.persist();
            count++;
        }
    }

    static void loadTags() throws URISyntaxException, IOException {
        List<String> lines = getLines("jobs");
        for (Tag tag : Tag.findAllTags()) {
            tag.remove();
        }
        for (String line : lines) {
            if (line.length() < 2)
                continue;

            Tag tag = new Tag();
            tag.setName(line);
            tag.persist();
        }
    }

    static void loadLocations() throws URISyntaxException, IOException {
        List<String> lines = getLines("countries");
        for (Location location : Location.findAllLocations()) {
            location.remove();
        }
        for (String name : lines) {
            if (name.length() < 2)
                continue;

            Location location = new Location();
            location.setName(name);
            location.persist();
        }
    }

    private static List<String> getLines(String filename) throws URISyntaxException, IOException {
        File countryFile = new File(Populate.class.getResource(filename).toURI());
        return readLines(countryFile, defaultCharset());
    }

}
