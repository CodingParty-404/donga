package com.cp.donga.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class FAQController {

    @GetMapping("/faq")
    public void faq(){
        log.info("Mamba!");
    }

}