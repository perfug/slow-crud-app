package com.jclarity.had_one_dismissal.web;

import com.jclarity.had_one_dismissal.domain.Applicant;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/seekers")
@Controller
@RooWebScaffold(path = "seekers", formBackingObject = Applicant.class)
public class SeekerController {
}
