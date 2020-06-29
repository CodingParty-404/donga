package com.cp.donga.service;

import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Scene;
import com.cp.donga.repository.SceneRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SceneServiceImpl implements SceneService {

    @Autowired
    private SceneRepository sceneRepository;

    @Override
    public Scene registerScene(Scene scene) {
        return sceneRepository.save(scene);
    }

    @Override
    public List<Scene> getSceneList(Long dongaId) {
        return sceneRepository.findByDonga(Donga.builder()
                                .dongaid(dongaId)
                                .build());
    }

    @Override
    public int getOneScene(String json, Long dongaId, Long index) {
        return sceneRepository.updateScene(json, Donga.builder()
                                            .dongaid(dongaId)
                                            .build(), index);
    }
}