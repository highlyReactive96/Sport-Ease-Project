package com.example.demo.dto;

import com.example.demo.pojo.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long id;
    private String name;
    private String email;
    private String mobileNo;
    private String aadharNo;
    private Gender gender;
}
