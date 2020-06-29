package com.cp.donga.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wno;

    private String location;

    private String status;

    private LocalDate datetoday; 

    private double lat;

    private double lng;

   
    
    public void setStatus(String status){
       
        if(status.indexOf("눈")!=-1)
            status = "눈";
        else if(status.indexOf("우박")!=-1)
            status = "눈";
        else if(status.indexOf("비")!=-1)
            status = "비";
        else if(status.indexOf("소나기")!=-1)
            status = "비";
        else if(status.indexOf("천둥번개")!=-1)
            status = "비";    
        else if(status.indexOf("구름")!=-1)
            status = "구름";
        else if(status.indexOf("안개")!=-1)
            status = "구름";        
        else
            status = "맑음";

        this.status = status;
    }


}