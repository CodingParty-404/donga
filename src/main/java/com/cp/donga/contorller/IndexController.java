package com.cp.donga.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class IndexController {

    @GetMapping(path = "/")
    public String root() {
        return "/index";
    }

    @GetMapping("/index")
    public String index() {
        log.info("message");
        return "/index";
    }

    @GetMapping("/about")
    public void about() {

    }
}