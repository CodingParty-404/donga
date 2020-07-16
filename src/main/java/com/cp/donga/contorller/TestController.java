package com.cp.donga.contorller;

import com.cp.donga.service.SceneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
public class TestController {
    
    @Autowired
    private SceneService sceneService;

    @GetMapping("test")
    public void test() {
        log.info("Test get.........................................");
    }

    @GetMapping("test2")
    public void test2( Long dongaId , Model model) {
        log.info("test2 get.......................................");
        model.addAttribute("list", sceneService.getSceneList(32L));
        model.addAttribute("dongaId",dongaId);
        
    }
    @GetMapping(value="makisutest")
    public void makitest() {
        log.info("makisu get...................................");
    }   
  

}