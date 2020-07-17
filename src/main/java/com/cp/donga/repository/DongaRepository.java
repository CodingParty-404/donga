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

    @Query("SELECT d FROM Donga d LEFT OUTER JOIN DongaMember m ON d.dongamember = m WHERE m.email = :email")
    List<Donga> getDongaList(@Param("email")String email);
}