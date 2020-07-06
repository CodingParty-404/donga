package com.cp.donga.service;

import java.util.List;

import com.cp.donga.domain.Scene;

public interface ShareService {
    

    public List<Scene> getSceneWhereDongaID(Long dongaId);

}