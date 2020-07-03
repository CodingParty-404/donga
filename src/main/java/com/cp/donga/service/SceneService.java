package com.cp.donga.service;

import java.util.List;

import com.cp.donga.domain.Scene;

public interface SceneService {
    
    public Scene registerScene(Scene scene);
    public List<Scene> getSceneList(Long dongaId);
    public int setOneScene(String json , Long dongaId, Long index);

}