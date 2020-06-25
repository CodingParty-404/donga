package com.cp.donga.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DongaDTO {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate enddate;
    private String title;

}