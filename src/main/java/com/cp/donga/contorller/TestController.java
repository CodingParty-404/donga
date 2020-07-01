package com.cp.donga.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class TestController {
    
    @GetMapping("test")
    public void test() {
        log.info("Test get.........................................");
    }

}