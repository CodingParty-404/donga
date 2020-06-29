package com.cp.donga.service;

import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Scene;
import com.cp.donga.repository.SceneRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ShareServiceimpl implements ShareService {

    @Autowired
    private SceneRepository sceneRepository;

    @Override
    public List<Scene> getSceneWhereDongaID(Long dongaId) {


        return sceneRepository.findByDonga(Donga.builder().dongaid(dongaId).build());

    }
        
}