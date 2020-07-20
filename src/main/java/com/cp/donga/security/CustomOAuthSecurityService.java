package com.cp.donga.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.cp.donga.domain.DongaMember;
import com.cp.donga.repository.DongaMemberRepository;
import com.cp.donga.repository.MemberRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomOAuthSecurityService extends DefaultOAuth2UserService {

    // private final SecurityMemberRepository memberRepository;
    private final DongaMemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User  auth2User =  super.loadUser(userRequest);

        log.info("----------------------------------------");
        log.info(auth2User);

        log.info(userRequest.getAdditionalParameters());

        String email  = auth2User.getAttribute("email");


        log.info("email..................." + email);


        //dongaMember 테이블에서 email로 회원을 조회해서 가져온다.
        // Optional<SecurityMember> result = memberRepository.findById(email);
        Optional<DongaMember> result = memberRepository.findByEmail(email);
        // 

        boolean checkLocal = false;

        //현재 옵셔널에 객체존재를 체크하고 boolean을 설정
        if(result.isPresent()){
            checkLocal = true;
        }

        //사용자 Oauth유저객체를 만들어서 반환한다 => success핸들러로 감
        return new CustomOAuth2User(auth2User, checkLocal);
    }
}
