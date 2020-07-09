package com.cp.donga.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "faq_join")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FAQJoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_faq_board_join", referencedColumnName = "faq_bno")
    private FAQBoard faqboard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_faq_category_join", referencedColumnName = "category_no")
    private FAQCategory faqcategory;

}