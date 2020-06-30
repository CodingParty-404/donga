package com.cp.donga.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Scene;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SceneRepository extends JpaRepository<Scene,Long> {
    
    List<Scene> findByDonga(Donga donga);


    @Transactional
    @Modifying
    @Query(value = "update Scene s set s.scenepath = :scenepath where s.donga = :donga and s.pagenum = :pagenum")
    int updateScene(@Param("scenepath")String scenepath, @Param("donga")Donga donga, @Param("pagenum") Long pagenum);

}