package com.cp.donga.Donga;

import com.cp.donga.domain.DongaMember;
import com.cp.donga.repository.DongaMemberRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DongaRepositoryTest {
    
    @Autowired
    DongaMemberRepository memberRepository;

    @Test
    public void insertMemberTest(){
        DongaMember member = DongaMember.builder().yourname("wewe").password("1234").build();

        System.out.println(memberRepository.save(member));

    }

    @Test
    public void insertDongaTest(){
        
    }
}