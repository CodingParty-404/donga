package com.cp.donga.contorller;

import java.util.List;

import com.cp.donga.domain.Scene;
import com.cp.donga.service.SceneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;


@Controller
@Log4j2
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private SceneService sceneService;
    
    @GetMapping("/read")
    public void readGet(@RequestParam Long dongaId, Model model){
        log.info("read page Get...........................");

        log.info(dongaId);

        List<Scene> list  =  sceneService.getSceneList(dongaId);

        list.forEach(vo -> log.info(vo));
        model.addAttribute("list", list);

    }
}