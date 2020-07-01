package com.cp.donga.contorller;

import java.util.List;

import com.cp.donga.domain.Picture;
import com.cp.donga.service.MapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class IndexController {

    @Autowired
    MapService mapService;

    @GetMapping("/index")
    public void index(){
        
    }

    @GetMapping({"/includestest","/includestest2"})
    public void include(){
        
    }

    @GetMapping("/includestest3")
    public void include3(Long dongaId, Model model){
        log.info("swiper called....");
        log.info(dongaId);
        // 1.전달받은 donga 정보를 가지고 donga객체를 생성한다.
        // 2.일치하는 picture를 가져온다.
        // 3.모델에 entity list를 전달한다.s
        
        //Donga의 id는 파라미터로 수집 되야함
        List<Picture> list = mapService.getPictures(dongaId);

        model.addAttribute("dongaId", dongaId);
        model.addAttribute("picturelist", list);
    }
}