package com.cp.donga.contorller;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Scene;
import com.cp.donga.repository.SceneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/scene")
public class SceneController {

    @Autowired
    private SceneRepository sceneRepository;

    @PostMapping("/gallery2")
    public String add(Long dongaId, String[] jList, Model model, RedirectAttributes rttr) {

        log.info(dongaId);
        log.info(Arrays.toString(jList));
        
        // 서버 주소 알아내는 코드
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String source_id = ip.getHostAddress();


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
                sceneRepository.save(scene);
            }

            // dongaId 전달
            model.addAttribute("dongaId", dongaId);
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        // model.addAttribute("dongaId", dongaId);
        rttr.addAttribute("dongaId",dongaId);
        // // [앨범 제작 페이지]로 리다이렉트
        return "redirect:/scene/makeDraw";

    }

    @GetMapping("/makeDraw")
    public void makeDrawGet(Long dongaId, Model model){
        log.info("makeDraw called...........................");
        
        List<Scene> list = sceneRepository.findByDonga(Donga.builder().dongaid(dongaId).build());

        model.addAttribute("list", list);
    }

}