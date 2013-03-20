package com.jclarity.had_one_dismissal.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jclarity.had_one_dismissal.domain.Applicant;
import com.jclarity.had_one_dismissal.domain.Company;
import com.jclarity.had_one_dismissal.domain.Location;
import com.jclarity.had_one_dismissal.domain.Tag;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring/webmvc-config.xml" })
public class PopulateTest {

    @Test
    public void loadsDataFromFiles() throws URISyntaxException, IOException {
        Populate.loadLocations();
        assertEquals(272L, Location.countLocations());

        Populate.loadTags();
        assertEquals(658L, Tag.countTags());

        Populate.loadNames();
        assertEquals(10000, Applicant.countApplicants());

        Populate.loadCompanies();
        assertEquals(2155, Company.countCompanys());
    }

}
