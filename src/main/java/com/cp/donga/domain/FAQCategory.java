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

@Entity(name = "faq_category")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class FAQCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_category_no")
    private Long categoryno;

    @Column(name = "faq_category_name")
    private String categoryname;

}