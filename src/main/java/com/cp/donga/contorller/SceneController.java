package com.cp.donga.contorller;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Picture;
import com.cp.donga.domain.Scene;
import com.cp.donga.dto.PictureDTO;
import com.cp.donga.service.MapService;
import com.cp.donga.service.SceneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/scene")
public class SceneController {

    @Autowired
    private MapService mapService;
    @Autowired
    private SceneService sceneService;

    @GetMapping("/gallery2")
    public void gallery2get(Model model, Long dongaId){
        log.info("gallery2 get called.....................");
        log.info("DongaId : " +dongaId);

        // List<Picture> pictures = pictureRepository.getPicturesAndWeather(Donga.builder().dongaid(dongaId).build());
        List<Picture> pictures =  mapService.getPictures(dongaId);
        List<PictureDTO> pictureDTOs = new ArrayList<>();

        Collections.sort(pictures);
        //가져온 pictures 길이만큼 dto를 생성해서 모델에 전달한다.
        pictures.stream().forEach(picture->{

            pictureDTOs.add(PictureDTO.builder()
                                      .filename(picture.getFilename())
                                      .capDate(picture.getCapdate())
                                      .weatherstatus(picture.getWeather() == null ? "없음" : picture.getWeather().getStatus())
                                      .build());
        });

        log.info(pictureDTOs.size());
        model.addAttribute("pictureList", pictureDTOs);
        model.addAttribute("dongaId", dongaId);
    }

    @PostMapping("/gallery2")
    public String add(Long dongaId, String[] jList, Model model, RedirectAttributes rttr) {

        log.info(dongaId);
        log.info(Arrays.toString(jList));
        
        // 서버 주소 알아내는 코드
        try {
            // InetAddress ip = InetAddress.getLocalHost();
            // String source_id = ip.getHostAddress();
            String source_id = "http://http://192.168.0.73:8080/";


            // jList 길이만큼 반복하며
            // db에 save
            for (int i = 0; i < jList.length; i++) {
                String newJson = jList[i];
                // 로컬호스트를 서버주소로 바꿔준다
                newJson = jList[i].replace("localhost", source_id);

                Scene scene = Scene.builder().donga(Donga.builder().dongaid(dongaId).build())
                                                                   .pagenum(i+1L)
                                                                   .scenepath(newJson)
                                                                   .build();
                sceneService.registerScene(scene);
            }

            // dongaId 전달
            model.addAttribute("dongaId", dongaId);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        // model.addAttribute("dongaId", dongaId);
        rttr.addAttribute("dongaId",dongaId);
        // // [앨범 제작 페이지]로 리다이렉트
        return "redirect:/scene/makedraw";
    }

    @GetMapping("/makedraw")
    public void makeDrawGet(Long dongaId, Model model){
        log.info("makeDraw called...........................");
        
        List<Scene> list = sceneService.getSceneList(dongaId);

        model.addAttribute("list", list);
        model.addAttribute("dongaId", dongaId);
    }
    @PostMapping("/makedraw")
    public RedirectView makeDrawPost(@RequestParam String[] jList, @RequestParam Long dongaId, RedirectAttributes rttr)
    {
        log.info("makedarw Post called .....................................");
        log.info(dongaId);


        Long index = 1L;
        for (String json : jList) {
            sceneService.setOneScene(json, dongaId, index++);
        }

        rttr.addAttribute("dongaId", dongaId);

        return new RedirectView("/share/read");
    }

    

}