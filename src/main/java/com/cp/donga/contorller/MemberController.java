package com.cp.donga.contorller;

import java.security.Principal;
import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.dto.MemberDTO;
import com.cp.donga.service.MemberService;
import com.cp.donga.service.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private StorageService storageService;

    @GetMapping("/road")
    public String road(){
        return "/road";
    }

    @GetMapping("/originSignup")
    public String origin(){
        return "/originSignup";
    }

    @GetMapping("/ex3")
    public String ex3(){
        return "/ex3";
    }

    @GetMapping("/ex2")
    public String ex2(){
        return "/ex2";
    }

    // 메인 페이지
    @GetMapping("/userindex")
    public String index() {
        return "/userindex";
    }

    // 회원가입 페이지
    @GetMapping("/user/signup")
    public String dispSignup(String email, Model model) {
        model.addAttribute("email", email);
        return "/signup";
    }

    // 회원가입 처리
    @PostMapping("/user/signup")
    public String execSignup(MemberDTO memberDto) {
        memberService.joinUser(memberDto);

        return "redirect:/user/login";
    }

    // 로그인 페이지
    @GetMapping("/user/login")
    public String dispLogin() {
        return "/user/login";
        
    }

    // 로그인 결과 페이지
    @GetMapping("/user/login/result")
    public String dispLoginResult() {
        return "/loginSuccess";
        // return "/user/myinfo";
    }

    // 로그아웃 결과 페이지
    @GetMapping("/user/logout/result")
    public String dispLogout() {
        return "/logout";
    }

    // 접근 거부 페이지
    @GetMapping("/user/denied")
    public String dispDenied() {
        return "/denied";
    }

    // 내 정보 페이지
    @GetMapping("/user/myinfo")
    public String dispMyInfo() {
        return "/myinfo";
    }

    // 어드민 페이지
    @GetMapping("/admin")
    public String dispAdmin() {
        return "/admin";
    }

    @GetMapping("/user/storage")
    public void storage(Model model,Principal principal){
        // log.info("!!!!!!!!!!id : " + id);
        // List<Donga> list = storageService.getDongaList(id);
        List<Donga> list = storageService.getDongaList(principal.getName());
        // System.out.println("yeon ug");
        log.info(principal.getName()+"-------------------------------------------------------------------------------------!!!!!!!!!!!");
        // System.out.println(principal.getName()+"----------------!!!!!!!!!!!");
        // log.info(list.toString());
        model.addAttribute("dongaList",list);
    }

    
}