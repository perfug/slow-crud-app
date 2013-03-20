package com.jclarity.had_one_dismissal.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/search/**")
@Controller
public class Search {

    @RequestMapping(method = RequestMethod.POST)
    public String post(HttpServletRequest request, Model uiModel) {
        String salaryRange = request.getParameter("salaryRange");
        String keywords = request.getParameter("keywords");
        String location = request.getParameter("location");
        String jobTitle = request.getParameter("jobTitle");



        return "redirect:search/index";
    }

    @RequestMapping
    public String index() {
        return "search/index";
    }

}
