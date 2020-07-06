package com.cp.donga.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.transaction.Transactional;

import com.cp.donga.domain.DongaMember;
import com.cp.donga.domain.MemberRole;
import com.cp.donga.domain.Role;
import com.cp.donga.dto.MemberDTO;
import com.cp.donga.repository.MemberRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    
    private MemberRepository memberRepository;

    // 회원가입 시 동작
    @Transactional
    public Long joinUser(MemberDTO memberDto) {
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        // dto를 Entity로 전환
        DongaMember member = memberDto.toEntity();

        // Entity에 member 권한 추가
        MemberRole roleM = MemberRole.builder().roleName("ROLE_MEMBER").build();
        member.addRole(roleM); 

        return memberRepository.save(member).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Optional<DongaMember> userEntityWrapper = memberRepository.findByEmail(userEmail);
        DongaMember userEntity = userEntityWrapper.get();

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (("hjleesig@gmail.com").equals(userEmail)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }

        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }
}