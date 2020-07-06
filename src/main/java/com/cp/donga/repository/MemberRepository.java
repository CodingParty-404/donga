package com.cp.donga.repository;

import java.util.Optional;

import com.cp.donga.domain.DongaMember;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<DongaMember, Long> {
    Optional<DongaMember> findByEmail(String userEmail);
}