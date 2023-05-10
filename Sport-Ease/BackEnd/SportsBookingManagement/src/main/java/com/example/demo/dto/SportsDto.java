package com.example.demo.dto;

import com.example.demo.pojo.SportsType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SportsDto {
   // private Long id;
    private SportsType sports;
    private int noOfGrounds;
    private Long academyId;
}