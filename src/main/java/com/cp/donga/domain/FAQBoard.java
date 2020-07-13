package com.cp.donga.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "faq_board")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class FAQBoard {

    @Id
    @Column(name="faq_bno")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long faqbno;

    @Column(name="faq_content")
    private String faqcontent;

    @Column(name="faq_title")
    private String faqtitle;

}