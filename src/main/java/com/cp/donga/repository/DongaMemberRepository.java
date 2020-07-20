package com.cp.donga.repository;

import java.util.Optional;

import com.cp.donga.domain.DongaMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DongaMemberRepository extends JpaRepository<DongaMember,Long> {
    
    Optional<DongaMember> findByEmail(String email);
}