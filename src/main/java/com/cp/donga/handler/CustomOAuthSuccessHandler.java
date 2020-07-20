package com.cp.donga.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cp.donga.security.CustomOAuth2User;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class CustomOAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("onAuthenticationSuccess...................................................................................");
        log.info(authentication);
        log.info("onAuthenticationSuccess...................................................................................");

        try{

            //이부분 왜 필요?
            CustomOAuth2User userInfo = (CustomOAuth2User)authentication.getPrincipal();
            //?
            if(userInfo.isCheckExist()){
                super.onAuthenticationSuccess(request,response,authentication);
                return;
            }
            // response.addHeader("username", userInfo.getEmail());
            response.sendRedirect("/user/signup?email="+userInfo.getEmail());

        }catch(Exception e){

        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
