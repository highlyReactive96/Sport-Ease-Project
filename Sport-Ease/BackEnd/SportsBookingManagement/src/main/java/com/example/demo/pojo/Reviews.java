package com.example.demo.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
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
public class Reviews extends BaseEntity {
	@Column(name="review_date")
	private LocalDate date;
	
	@Column(name="review_desc", length = 500)
	private String description;
	
	@Column(name="stars")
	private Integer stars;
	
	@OneToOne
	@JoinColumn(name="player_id")
	private Player player;
	
	@ManyToOne
	@JoinColumn(name="academy_id")
	private Academy academy;
	
}
