package com.cp.donga.service;

import com.cp.donga.repository.DongaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DongaServiceImpl implements DongaService {
    
    @Autowired
    DongaRepository DongaRepository;

    @Override
    public String getDongaTitle(Long dongaId) {
        return DongaRepository.findById(dongaId).get().getTitle();
    }
    
}