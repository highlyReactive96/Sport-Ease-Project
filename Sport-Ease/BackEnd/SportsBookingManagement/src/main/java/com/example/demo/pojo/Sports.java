package com.example.demo.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@ToString
public class Sports extends BaseEntity{
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sports_name",length = 20)
	private SportsType sports;
	
	@Column(name = "no_of_ground",nullable = false)
	private Long noOfGrounds;
	
	@ManyToOne
	@JoinColumn(name ="academy_id")
	@JsonIgnore
	private Academy academy;
	
	@OneToMany(mappedBy = "sports",cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Ground> groundList = new HashSet<>();



	
	 public static Sports fromString(String sport) {
	        for (Sports s : Sports.values()) {
	            if (s.toString().equalsIgnoreCase(sport)) {
	                return s;
	            }
	        }
	        throw new IllegalArgumentException("Invalid sport: " + sport);
	    }

	 public static Sports[] values() {
	        return Sports.values();
	    }



	

}
