package com.cp.donga.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.DongaMember;
import com.cp.donga.repository.DongaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class StorageServiceImpl implements StorageService {

    @Autowired
    private DongaRepository dongaRepository;


    @Override
    public List<Donga> getDongaList(String email) {
        log.info(email);

        return dongaRepository.getDongaList(email
        );
    }
}