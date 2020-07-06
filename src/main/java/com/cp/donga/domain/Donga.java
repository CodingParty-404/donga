package com.cp.donga.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Donga {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dongaid;

    private String title;

    private LocalDate stdate;

    private LocalDate eddate;

    private LocalDate regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    private DongaMember dongamember;

}