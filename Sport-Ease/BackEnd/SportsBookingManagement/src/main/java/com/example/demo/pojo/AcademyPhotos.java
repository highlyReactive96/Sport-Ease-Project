package com.example.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AcademyPhotos extends BaseEntity{

	@Column(length = 1000,nullable = false)
	private String photo;
	
	@ManyToOne
	@JoinColumn(name = "academy_id")
	@JsonIgnore
	private Academy academy;
}
