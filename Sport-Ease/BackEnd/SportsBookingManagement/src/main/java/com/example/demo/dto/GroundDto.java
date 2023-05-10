package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroundDto {
    private Long id;
    private String groundDescription;
    private Boolean isEquipmentAvailable;
    private SportsDto sports;
    private AcademyDto academy;
}

