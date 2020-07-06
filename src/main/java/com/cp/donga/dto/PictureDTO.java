package com.cp.donga.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PictureDTO implements Comparable<PictureDTO>{
    Double lat , lng;
    String filename;
    String projectName;
    String weatherstatus;
    LocalDate capDate;

    @Override
    public int compareTo(PictureDTO photoDTO) {
        return this.capDate.compareTo(photoDTO.capDate);
    }
    
}