package com.example.demo.pojo;


import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TimeSlot extends BaseEntity{
	
	@Column(name="availability_date")
	private LocalDate date;

	@Column(name="slot_start_time",nullable = false)
	@Temporal(TemporalType.TIME)
	private Date slotDateTimeStart;
	
	@Column(name="slot_end_time",nullable = false)
	@Temporal(TemporalType.TIME)
	private Date slotDateTimeEnd;
	
	@Column(name = "rent",nullable = false)
	private Integer rentAmount;
	
	@Column(name="available",nullable = false)
	private Boolean isAvailable;
	
	@ManyToOne
	@JoinColumn(name="ground_id")
	private Ground ground;
}
