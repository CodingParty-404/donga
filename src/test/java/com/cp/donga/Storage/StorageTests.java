package com.cp.donga.Storage;

import javax.transaction.Transactional;

import com.cp.donga.repository.DongaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class StorageTests {
    
    
    @Autowired
    DongaRepository dongaRepository;
    
    @Test
    @Transactional
    public void DataTest(){

        dongaRepository.getDongaList("mamba@gmail.com").forEach((i) -> log.info(i));        


    }

}