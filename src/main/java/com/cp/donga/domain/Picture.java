package com.cp.donga.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private double lat;

    private double lng;

    private LocalDate capdate;

    private String filename;

    @ManyToOne(fetch = FetchType.LAZY)
    private Weather weather;


    @JsonIgnore //템플렛에서 이 객체를 참조할때 lazy로딩으로 인한 오류가 안나게, json으로 변환을 무시한다
    @ManyToOne(fetch = FetchType.LAZY)
    private Donga dongafk;

}