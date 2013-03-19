package com.jclarity.had_one_dismissal.web;

import com.jclarity.had_one_dismissal.domain.Seeker;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/seekers")
@Controller
@RooWebScaffold(path = "seekers", formBackingObject = Seeker.class)
public class SeekerController {
}
