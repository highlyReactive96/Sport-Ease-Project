package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class TimeSlotDto {
	    private Long id;
	    private LocalDate date;
	    private LocalTime slotDateTimeStart;
	    private LocalTime slotDateTimeEnd;
	    private BigDecimal rentAmount;
	    private Boolean isAvailable;
	    private Long groundId;
	}


