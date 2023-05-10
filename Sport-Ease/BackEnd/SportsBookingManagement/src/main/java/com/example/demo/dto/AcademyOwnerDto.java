package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademyOwnerDto {
    private String ownerName;
    private String mobileNo;
    private String email;
    private String gender;
    private String ownerPhoto;
    private String ownerAddress;
    private String aadharNo;
    private LocalDate regDate;
}

