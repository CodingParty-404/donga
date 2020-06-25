package com.cp.donga.repository;

import com.cp.donga.domain.DongaMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DongaMemberRepository extends JpaRepository<DongaMember,Long> {
    
}