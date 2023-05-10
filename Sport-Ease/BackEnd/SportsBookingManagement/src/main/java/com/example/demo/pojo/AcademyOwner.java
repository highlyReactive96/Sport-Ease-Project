package com.example.demo.pojo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AcademyOwner extends BaseEntity{
	
	@Column(name = "owner_name", length = 50,nullable = false)
	private String ownerName;
	
	@Column(name = "mobile_no",nullable = false,unique = true,columnDefinition="CHAR(10)")
	private String mobileNo;
		
	@Column(length = 50,unique = true)
	private String email;
	
	@Column(name = "owner_photo", length = 50)
	private String ownerPhoto;
	
	@Column(name = "owner_address", length = 100,nullable = true)
	private String ownerAddress; 
	
	@Column(name = "aadhar_no",length=12,nullable = false,unique = true,columnDefinition="CHAR(14)")
	private String aadharNo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type",length = 12)
	private Role role;
	
	@Column(name = "reg_date")
	private LocalDate regDate;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 6)
	private Gender gender;
	
	@OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
	private Set<Academy> academyList = new HashSet<>();
}
