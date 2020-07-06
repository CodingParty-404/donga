package com.cp.donga.dto;

import java.time.LocalDateTime;

import com.cp.donga.domain.DongaMember;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String picture;
    private String homeaddress;
    private String citySelect;
    private String yourname;
    String secondpassword;

    public DongaMember toEntity(){
        return DongaMember.builder()
                .id(id)
                .email(email)
                .password(password)
                .homeaddress(homeaddress)
                .picture(picture)
                .citySelect(citySelect)
                .yourname(yourname)
                .secondpassword(secondpassword)
                .build();
    }

    @Builder
    public MemberDTO(Long id, String email, String password, String picture, String homeaddress, String citySelect, String yourname, String secondpassword) {
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