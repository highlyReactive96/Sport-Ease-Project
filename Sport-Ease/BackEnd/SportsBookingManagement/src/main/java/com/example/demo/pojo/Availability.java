//package com.example.demo.pojo;
//
//import java.time.Duration;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@Entity
//public class Availability extends BaseEntity {
//	
//	@Column(name="availability_date")
//	private LocalDate date;
//	
//	@Column(name = "slot_time", columnDefinition = "INTERVAL HOUR TO MINUTE DEFAULT INTERVAL '1:00' HOUR TO MINUTE")
//	private Duration slotTime;
//	
//	@ManyToOne
//	@JoinColumn(name="ground_id")
//	private Ground ground;
//	
//	@OneToMany(mappedBy = "availability",cascade = CascadeType.ALL)
//	private List<TimeSlot> timeSlotList=new ArrayList<TimeSlot>();
//
//}
