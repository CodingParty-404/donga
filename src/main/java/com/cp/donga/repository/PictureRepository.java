package com.cp.donga.repository;

import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.Picture;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PictureRepository extends JpaRepository<Picture,Long>{
    
    @Query("select p from Picture p where p.dongafk = :did")
    List<Picture> getPictures(@Param("did")Donga did);

    @EntityGraph(attributePaths = {"weather","dongafk"})
    @Query("select p from Picture p where p.dongafk = :did")
    List<Picture> getPicturesAndWeather(@Param("did")Donga did);
}