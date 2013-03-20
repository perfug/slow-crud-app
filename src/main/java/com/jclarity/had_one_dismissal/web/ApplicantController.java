package com.jclarity.had_one_dismissal.web;

import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jclarity.had_one_dismissal.domain.Applicant;

@RequestMapping("/applicant")
@Controller
@RooWebScaffold(path = "applicant", formBackingObject = Applicant.class)
public class ApplicantController {
}
