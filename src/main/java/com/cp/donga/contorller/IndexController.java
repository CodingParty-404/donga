package com.cp.donga.contorller;

import java.io.Console;
import java.io.File;
import java.security.Principal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Picture;
import com.cp.donga.domain.Scene;
import com.cp.donga.dto.PictureDTO;
import com.cp.donga.service.MapService;
import com.cp.donga.service.SceneService;
import com.cp.donga.service.StorageService;
import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class IndexController {

    @Autowired
    private MapService mapService;

    @Autowired
    private SceneService sceneService;

    @Autowired
    private StorageService storageService;
    
    private final String ROOT_PATH = "C:\\cp\\donga\\src\\main\\resources\\static\\pictures\\";

    @GetMapping("/index")
    public void index(){
     log.info("message");  
    }

    @GetMapping("/about")
    public void about(){
        
    }

    @GetMapping("/storage")
    public void storage(Model model, Principal principal){
        // log.info("!!!!!!!!!!id : " + id);
        log.info(principal.getName()+"................");
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        // User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // String user_id = user.getUsername();
        // // log.info(user);
        // log.info(user.getUserDatabase());

        // List<Donga> list = storageService.getDongaList(id);
        List<Donga> list = storageService.getDaongaList2(principal.getName());
        // log.info("----------------!!!!!!!!!!!");
        log.info(list.toString()+"sssssh");

        model.addAttribute("dongaList",list);
    }

    @GetMapping("/includestest")
    public void include(){
        
    }

    @GetMapping("/includestest2")
    public void include2(Long dongaId, Model model){
        log.info("get gallery call...............");
        log.info("dongaID : "+dongaId);
        // 1.파일이 들어있는 폴더의 경로이름을 받는다.
        // 2.경로의 모든 파일을 가져온다.
        // 3.파일의 이름과, 위치 및 시간정보를 저장하고, dto List를 만든다.
        // 4.리스트를 시간순서대로 정렬한다.
        // 5.모델에 저장해서 보낸다.
        String uploadPath = ROOT_PATH + dongaId;
        log.info("uploadPath"+uploadPath);

        List<PictureDTO> list = new ArrayList<>();
        File dongaDirectory = new File(uploadPath);

        File[] pictureFiles = dongaDirectory.listFiles();

        log.info(dongaDirectory==null);
        log.info(pictureFiles==null);
        for (File picturefile : pictureFiles) {
            // 파일 이름.
            String fileName = picturefile.getName();
            double lat = 0;
            double lng = 0;
            LocalDate captureDate = LocalDate.now();

            // 메타데이터 추출(위치 및 찍은 시간)
            try {
                Metadata metadata = ImageMetadataReader.readMetadata(picturefile);
                Iterator<GpsDirectory> it = metadata.getDirectoriesOfType(GpsDirectory.class).iterator();
                Iterator<ExifSubIFDDirectory> exifIt = metadata.getDirectoriesOfType(ExifSubIFDDirectory.class)
                        .iterator();

                // 위치 정보 추출
                while (it.hasNext()) {
                    GeoLocation location = it.next().getGeoLocation();
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    log.info("lat: " + lat + " lng: " + lng);
                }

                // 시간 정보 추출
                while (exifIt.hasNext()) {
                    captureDate = exifIt.next().getDateDigitized().toInstant().atZone(ZoneId.systemDefault())
                            .toLocalDate();
                }

            } catch (Exception e) {
                log.info(e);
            }
            
            list.add(PictureDTO.builder().projectName(Long.toString(dongaId)).filename(fileName).lat(lat).lng(lng)
                    .capDate(captureDate).build());
        } // end of for loop

        // 찍힌 시간 순서대로 정렬
        Collections.sort(list);
        list.forEach(e -> {
            log.info(e.getCapDate());
        });

        model.addAttribute("pictureDTO", list);
        model.addAttribute("dongaId", dongaId);
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

    @GetMapping("/includestest4")
    public void includeRead(@RequestParam Long dongaId, Model model){
        log.info("read page Get...........................");

        log.info(dongaId);

        List<Scene> list  =  sceneService.getSceneList(dongaId);

        list.forEach(vo -> log.info(vo));
        model.addAttribute("list", list);
        
    }
}