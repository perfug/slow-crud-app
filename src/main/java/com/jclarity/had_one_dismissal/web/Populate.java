package com.jclarity.had_one_dismissal.web;

import static com.google.common.io.Files.readLines;
import static java.nio.charset.Charset.defaultCharset;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jclarity.had_one_dismissal.domain.Applicant;
import com.jclarity.had_one_dismissal.domain.Company;
import com.jclarity.had_one_dismissal.domain.Location;
import com.jclarity.had_one_dismissal.domain.Tag;

@RequestMapping("/populate/**")
@Controller
public class Populate {

    private static final Logger LOGGER = LoggerFactory.getLogger(Populate.class);

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        LOGGER.debug("Repopulating the database");
        try {
            loadLocations();
            loadTags();
            loadNames();
            loadCompanies();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return "populate/index";
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
            applicant.setFirstName(split[0]);
            applicant.setSurName(split[1]);
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
