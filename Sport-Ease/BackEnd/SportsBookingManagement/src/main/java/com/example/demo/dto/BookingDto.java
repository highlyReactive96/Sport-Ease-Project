package com.example.demo.dto;

import com.example.demo.pojo.TimeSlot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {

    private Long id;
    private String bookingCode;
    private TimeSlot slot;
    private Long academyId;
    private Long playerId;

}
