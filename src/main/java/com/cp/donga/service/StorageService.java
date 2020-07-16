package com.cp.donga.service;

import java.util.List;

import com.cp.donga.domain.Donga;

public interface StorageService {
    public List<Donga> getDongaList(String email);
}