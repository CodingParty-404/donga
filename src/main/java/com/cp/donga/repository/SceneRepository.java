package com.cp.donga.repository;

import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Scene;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRepository extends JpaRepository<Scene,Long> {
    
    List<Scene> findByDonga(Donga donga);
}