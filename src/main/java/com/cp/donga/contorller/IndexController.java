package com.cp.donga.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public void index(){
        
    }

    @GetMapping("/includesTest")
    public void include(){
        
    }

    @GetMapping("/includesTest2")
    public void include2(){
        
    }
}