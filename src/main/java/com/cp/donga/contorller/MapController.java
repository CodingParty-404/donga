package com.cp.donga.contorller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Picture;
import com.cp.donga.dto.DongaDTO;
import com.cp.donga.dto.PictureDTO;
import com.cp.donga.service.MapService;
import com.drew.imaging.ImageMetadataReader;
import com.drew.lang.GeoLocation;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.drew.metadata.exif.GpsDirectory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
@RequestMapping("/map")
@Log4j2
public class MapController {


    @Autowired
    private MapService mapService;


    private final String ROOT_PATH = "\\\\192.168.0.74\\cpst\\was\\tomcat9\\webapps\\ROOT\\pictures\\";
    // private final String ROOT_PATH = "C:\\cp\\donga\\src\\main\\resources\\static\\pictures\\";

    @GetMapping("/set2")
    public void set(){
    }

    @PostMapping("/set2")
    public String setPost(DongaDTO dongaDTO, MultipartFile[] upload, RedirectAttributes rttr){

        log.info(dongaDTO);
        log.info(upload.length);

        String uploadPath = ROOT_PATH ;

        // DTO를 엔티티로 변환해 save하고 insert_id를 가져온다.
        //
        // Donga donga = Donga.builder()
        //                     .eddate(dongaDTO.getEnddate())
        //                     .stdate(dongaDTO.getStartdate())
        //                     .regdate(LocalDate.now())
        //                     .title(dongaDTO.getTitle())
        //                     .member(Member.builder().mid(1L).build())
        //                     .dongamember(DongaMember.builder().mid(1L).build())
        //                     .build();


        Donga donga = mapService.registerDonga(dongaDTO);
        Long dongaId = donga.getDongaid();
        log.info(uploadPath+dongaId);

        File upDirectory = new File(uploadPath, Long.toString(dongaId)); //dongaid로 만든 directory 

        upDirectory.mkdirs();

        for (MultipartFile upfile : upload) {
            File pictureFile = new File(upDirectory, upfile.getOriginalFilename());

            try{
                upfile.transferTo(pictureFile); // upload한 데이터를 file객체에 저장
                log.info("picture name: "+pictureFile.getName());
                log.info("picture is exists: "+pictureFile.exists());
                log.info("picture szie: "+pictureFile.length());
            }
            catch(Exception e){
                log.info(e);
            }
        }
        
        rttr.addAttribute("dongaId", dongaId);
        return "redirect:/map/gallery2";
    }


    @GetMapping("/gallery2")
    public void gallery(Long dongaId, Model model) {
        log.info("get gallery call...............");
        log.info("dongaID : "+dongaId);
        // 1.파일이 들어있는 폴더의 경로이름을 받는다.
        // 2.경로의 모든 파일을 가져온다.
        // 3.파일의 이름과, 위치 및 시간정보를 저장하고, dto List를 만든다.
        // 4.리스트를 시간순서대로 정렬한다.
        // 5.모델에 저장해서 보낸다.
        String uploadPath = ROOT_PATH + dongaId;
        List<PictureDTO> list = new ArrayList<>();
        File dongaDirectory = new File(uploadPath);
        File[] pictureFiles = dongaDirectory.listFiles();
        log.info("++++++++++++++++++++++++++++++++++++++++");
        log.info(uploadPath);
        log.info(pictureFiles);
        log.info("++++++++++++++++++++++++++++++++++++++++");

        for (File picturefile : pictureFiles) {
            // 파일 이름.
            String fileName = picturefile.getName();
            double lat = 0;
            double lng = 0;
            LocalDate captureDate = LocalDate.of(2100, 12, 31);

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

    @PostMapping("/gallery2")
    public RedirectView postGallery(String[] filenames, Long dongaId, RedirectAttributes rttr) {
        log.info("post gallery called..................");
        log.info(dongaId);


        // String projectName = "project1";
        String uploadPath = ROOT_PATH + dongaId;

        List<Picture> list = new ArrayList<>();
        File dongaDirectory = new File(uploadPath);

        // 1.삭제를 원하는 file이름을 전달 받아 삭제한다.
        filenames = filenames == null ? new String[] {} : filenames; // 유효성 검사
        for (String filename : filenames) {
            File file = new File(uploadPath, filename);
            log.info(file.delete()); // 삭제 성공시 true;
        }

        File[] pictureFiles = dongaDirectory.listFiles(); // 삭제하고 남은 사진객체를 가져온다.

        //남은 파일만큼 반복해서 썸네일을 생성하고, 메타데이터를 추출한다.
        for (File picturefile : pictureFiles) {
            // 파일 이름.
            String fileName = picturefile.getName();
            String thumName = "s_" + fileName;

            File saveThumbFile = new File(uploadPath,thumName);

            double lat = 0;
            double lng = 0;
            LocalDate captureDate = LocalDate.now();

         
            try {
                // 메타데이터 추출(위치 및 찍은 시간)
                Metadata metadata = ImageMetadataReader.readMetadata(picturefile);
                Iterator<GpsDirectory> it = metadata.getDirectoriesOfType(GpsDirectory.class).iterator();
                Iterator<ExifSubIFDDirectory> exifIt = metadata.getDirectoriesOfType(ExifSubIFDDirectory.class).iterator();                       

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

                  // thumbnail 저장할 폴더 outputStream 지정
                //   FileOutputStream thumbnail = new FileOutputStream(picturefile);
  
                  // -------------Thumnailator 기능
                  // 원본데이터의 inputStream 연결 및 thumbnail 설정
                  Thumbnails.of(picturefile).crop(Positions.CENTER_LEFT) // 중앙기준으로 사진자름
                          .size(400, 300) // 리사이징
                          .keepAspectRatio(true) // 비율유지
                          .toFile(saveThumbFile);
  
                  log.info("............................");
                  // outputStream close
                //   thumbnail.close();

            } catch (Exception e) {
                log.info(e.getMessage());
            }

            list.add(Picture.builder()
                    // .projectName(projectName)
                    .filename(fileName).lat(lat).lng(lng).dongafk(Donga.builder().dongaid(dongaId).build())// test용 고정 donga와 연결
                    .capdate(captureDate)
                    // .dongafk(dongafk) //첫번째 여행정보입력시 donga 만들고
                    // 파라미터로 정보 받아야함
                    .build());
        } // end of for loop

        mapService.registerPictures(list);
        rttr.addAttribute("dongaId",dongaId);
        
        return new RedirectView("/map/swiper2");
    }

    @GetMapping("/swiper2")
    public void swiper(Long dongaId, Model model) { // 파라미터로 dongaId를 받는다.
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

    // 오늘자 폴더구조 문자열 반환
    private String getFolder() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");
        Date date = new Date(); // 오늘자 날짜데이터 생성

        String result = dateFormat.format(date);
        return result;
    }

    @GetMapping("/progress")
    public void progress() {

        log.info("get progress call...............");
    }

    @GetMapping("/dragdrop")
    public void dragDrop() {
        log.info("get dragdrop call..............");
    }


    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<PictureDTO>> upload(@RequestBody MultipartFile[] uploadFiles) {

        log.info("upload called.........");
        // 1.업로드요청으로 들어온 파일의 전체데이터 및 메타데이터를 하나씩 분석한다.
        // 2.파일 이름을 dto에 저장한다.
        // 3.위도와 경도를 빼내서 dto에 저장한다.

        List<PictureDTO> list = new ArrayList<>();
        String uploadPath = "C:\\donga\\src\\main\\resources\\static\\pictures";
        log.info(uploadFiles.length);
        for (MultipartFile upfile : uploadFiles) {

            String fileName = UUID.randomUUID() + "_" + upfile.getOriginalFilename();
            String thumName = "s_" + fileName;

            // static/pictures 에 일관 업로드
            File saveFile = new File(uploadPath, fileName);

            File thumFile = new File(uploadPath, thumName);

            try {
                upfile.transferTo(saveFile); // upload한 데이터를 file객체에 저장

                // thumbnail 저장할 폴더 outputStream 지정
                FileOutputStream thumbnail = new FileOutputStream(thumFile);
                log.info(thumbnail);

                // -------------Thumnailator 기능
                // 원본데이터의 inputStream 연결 및 thumbnail 설정
                Thumbnails.of(saveFile).crop(Positions.CENTER_LEFT) // 중앙기준으로 사진자름
                        .size(300, 300) // 리사이징
                        .keepAspectRatio(true) // 비율유지
                        .toFile(thumFile);

                log.info("............................");
                // outputStream close
                thumbnail.close();

                Metadata metadata = ImageMetadataReader.readMetadata(saveFile);

                // 메타데이터의 각 디렉토리 및 디렉토리 클래스 이름 출력
                // metadata.getDirectories().forEach(e->{
                // System.out.println(e.getClass().getName());
                // e.getTags().stream().forEach(t->{

                // //각 디렉토리별 상세 내용 태그명 및 내용 출력
                // System.out.println("tagName :"+t.getTagName()+":"+t.getDescription());
                // // t.getDescription();
                // });
                // });

                Iterator<GpsDirectory> it = metadata.getDirectoriesOfType(GpsDirectory.class).iterator();
                Iterator<ExifSubIFDDirectory> exifIt = metadata.getDirectoriesOfType(ExifSubIFDDirectory.class)
                        .iterator();

                // DTO에 넣기 위한 위도 경도 및 시간 변수
                double lat = 0;
                double lng = 0;
                Date capDate = new Date();

                // 위치 정보 추출
                while (it.hasNext()) {
                    GeoLocation location = it.next().getGeoLocation();
                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    log.info("lat: " + lat + " lng: " + lng);
                }

                // 시간 정보 추출
                while (exifIt.hasNext()) {
                    // Date capDate =
                    // exifIt.next().getDateDigitized(TimeZone.getTimeZone("Asia/Seoul"));
                    capDate = exifIt.next().getDateDigitized(); // Sun Apr 22 01:21:36 KST 2018
                }

                // 사진DTO생성 및 ResponseEntity에 저장할 list에 add
                list.add(PictureDTO.builder().filename(fileName).lat(lat).lng(lng)
                        .capDate(capDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).build());

            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}