package com.example.demo.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Player extends BaseEntity {
	@Column(name = "player_name", length = 50,nullable = false)
	private String name;
	
	@Column(name = "player_email", length = 50,nullable = false)
	private String email;
	
	@Column(name = "player_mobno",nullable = false,unique = true,columnDefinition="CHAR(10)")
	private String mobileNo;
	
	@Column(name = "player_aadharno", nullable = false,unique = true,columnDefinition="CHAR(12)")
	private String aadharNo;

	@Enumerated(EnumType.STRING)
	@Column(name = "player_gender",nullable = false,length = 6)
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type",length = 12)
	private Role role;
	
	@OneToMany(mappedBy = "player",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Booking> bookingList=new HashSet<>();
	
}
