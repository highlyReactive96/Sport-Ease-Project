package com.example.demo.pojo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Booking extends BaseEntity{

	private String bookingCode;
	
	@OneToOne
	private TimeSlot slot;
	
	@ManyToOne
	@JoinColumn(name ="academy_id")
	private Academy academy;
	
	@ManyToOne
	@JoinColumn(name="player_id")
	private Player player;

}
