package com.example.demo.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@BatchSize(size = 200)
@ToString
public class Academy extends BaseEntity{
	
	@Column(length = 500,nullable = false)
	private String description;
	
	@Column(length = 30,nullable = false)
	private String location;
	
	@Column(length = 30,nullable = false,unique = true,columnDefinition="CHAR(10)")
	private String contactDetails;
	
	@Column(length = 50,nullable = false)
	private String academyName;
	
	@Column(length = 100,nullable = false)
	private String address;
	
	@Column(length = 100,nullable = false)
	private String city;
	
	@Column(name = "no_of_sports",nullable =false)
	private Integer noOfSports;
	
	@OneToOne
	@JoinColumn(name = "owner_id")
	@JsonIgnore
	private AcademyOwner owner;
	
//	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private List<AcademyPhotos> photoList = new ArrayList<>();
	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<AcademyPhotos> photoList = new HashSet<>();
	
//	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
//	@LazyCollection(LazyCollectionOption.FALSE)
//	private S<Sports> sportList = new ArrayList<>();
	
	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Sports> sportList = new HashSet<Sports>();
	
	
	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Ground> groundList = new HashSet<Ground>();
	
//	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
//	private List<Booking> bookingList = new ArrayList<>();
	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Booking> bookingList = new HashSet<>();

//	@OneToMany(mappedBy = "academy")
//	private List<Reviews> reviewList = new ArrayList<Reviews>();
	@OneToMany(mappedBy = "academy",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Reviews> reviewList = new HashSet<Reviews>();

}
