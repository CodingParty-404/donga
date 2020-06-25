package com.cp.donga.repository;

import com.cp.donga.domain.Scene;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRepository extends JpaRepository<Scene,Long> {
    
}