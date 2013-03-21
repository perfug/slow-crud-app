package com.jclarity.had_one_dismissal.web;

import com.jclarity.had_one_dismissal.domain.JobListing;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/joblistings")
@Controller
@RooWebScaffold(path = "joblistings", formBackingObject = JobListing.class)
public class JobListingController {
}
