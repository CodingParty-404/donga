package com.cp.donga.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.cp.donga.domain.Donga;

import org.springframework.ui.Model;

public interface StorageService {
    // public List<Donga> getDongaList(Long id);

    public List<Donga> getDaongaList2(String email);
}