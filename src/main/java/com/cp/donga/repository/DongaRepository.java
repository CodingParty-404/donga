package com.cp.donga.repository;

import com.cp.donga.domain.Donga;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DongaRepository extends JpaRepository<Donga, Long> {
    
}