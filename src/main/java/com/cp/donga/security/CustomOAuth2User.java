package com.cp.donga.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Data
public class CustomOAuth2User implements OAuth2User {

    private String email;
    private OAuth2User target;
    private boolean checkExist;

    public CustomOAuth2User(OAuth2User oAuth2User, boolean checkExist){

        this.email = oAuth2User.getAttribute("email");
        this.target = oAuth2User;
        this.checkExist = checkExist;

    }

    @Override
    public Map<String, Object> getAttributes() {
        return target.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return target.getAuthorities();
    }

    @Override
    public String getName() {
        return target.getName();
    }
}
