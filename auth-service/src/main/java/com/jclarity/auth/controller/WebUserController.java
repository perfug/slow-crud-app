package com.jclarity.auth.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jclarity.auth.domain.WebUser;

@RequestMapping("/webusers")
@Controller
@RooWebScaffold(path = "webusers", formBackingObject = WebUser.class)
@RooWebFinder
@RooWebJson(jsonObject = WebUser.class)
public class WebUserController {

    @RequestMapping(params = "find=ByUsernameEquals", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> jsonFindWebUsersByUsernameEquals(@RequestParam("username") String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(WebUser.findWebUsersByUsernameEquals(username).getResultList().get(0).toJson(), headers, HttpStatus.OK);
    }
}
