package com.cp.donga.repository;

import java.util.List;

import com.cp.donga.domain.Donga;
import com.cp.donga.domain.DongaMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DongaRepository extends JpaRepository<Donga, Long> {
    @Query("select a from Donga a where dongamember = :id")
    List<Donga> getDongaList(@Param("id")DongaMember id);
}