package com.cp.donga.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class DongaMember {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String secondpassword;

    @Column
    private String picture;

    @Column
    private String homeaddress;

    @Column
    private String citySelect;

    @Column
    private String yourname;

    // 한번에 회원과 회원권한까지 로딩하는 스타일로 할거라서 EAGER를 걸었다
    // 회원을 저장할때 권한까지 저장할거야
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "dongamember")
    private Set<MemberRole> roleSets;

    // set 대신 메서드
    public void addRole(MemberRole role){
        if(roleSets == null){
            roleSets = new HashSet<>();
        }
        roleSets.add(role);
    } 
    
    @Builder
    public DongaMember(Long id, String email, String password, String picture, String homeaddress, String citySelect, String yourname, String secondpassword) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.homeaddress = homeaddress;
        this.citySelect = citySelect;
        this.yourname = yourname;
        this.secondpassword = secondpassword;
    }
}