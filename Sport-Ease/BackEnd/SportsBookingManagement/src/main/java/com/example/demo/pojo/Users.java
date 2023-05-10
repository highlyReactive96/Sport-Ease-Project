package com.example.demo.pojo;

import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users extends BaseEntity {
	@Column(length = 20,nullable = false)
	private String password;
	
	@Column(length = 50,unique = true)
	private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type",length = 12)
	private Role role;

	public Users(String password, Role role) {
		super();
		this.password = password;
		this.role = role;
	}
	
	public User toUser() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
		User user = new User(email,password, 
				Collections.singletonList(authority));
		return user;
	}

}
