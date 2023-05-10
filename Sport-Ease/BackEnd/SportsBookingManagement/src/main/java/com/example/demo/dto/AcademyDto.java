package com.example.demo.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.pojo.AcademyPhotos;
import com.example.demo.pojo.Sports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademyDto {
	 private Long id;
	    private String description;
	    private String location;
	    private String contactDetails;
	    private String academyName;
	    private String address;
	    private String city;
	    private Integer noOfSports;
//	    private List<AcademyPhotos> photoList = new ArrayList<>();
//	    private List<Sports> sportList = new ArrayList<>();
//	    private Set<Sports> sportList=new HashSet<>();


}
