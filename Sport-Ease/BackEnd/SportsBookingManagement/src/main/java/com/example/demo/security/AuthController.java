package com.example.demo.security;


import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResp;

import lombok.extern.slf4j.Slf4j;

//import org.springframework.security.core.

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/authenticate")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping
	public ResponseEntity<String> authenticate(@RequestBody AuthRequest cred)
	{
		
		try {
			Authentication auth = new UsernamePasswordAuthenticationToken(cred.getEmail(), cred.getPassword());
			System.out.println("BEFORE: " + auth);
			auth = authManager.authenticate(auth);
			System.out.println("AFTER: " + auth);
			User user = (User)auth.getPrincipal();
			String Role = null;
			Collection<GrantedAuthority> authorities = user.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				Role = grantedAuthority.getAuthority();
			}
			String token = jwtUtils.generateToken(user.getUsername(),Role);
			return ResponseEntity.ok(token);
		}catch (BadCredentialsException e) {
			return ResponseEntity.notFound().build();
		}
//		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(cred.getEmail(),
//				cred.getPassword());
//		log.info("auth token " + authToken);
//			// authenticate the credentials
//			Authentication authentication = authManager.authenticate(authToken);
//			log.info("auth token again " + authentication.getPrincipal().getClass());
//			CustomUserDetails userDetails=(CustomUserDetails)authentication.getPrincipal();
//			Users user = userDetails.getUser();
//			AuthResp resp = mapper.map(user,AuthResp.class);
//			resp.setToken(utils.generateJwtToken(authentication));
//			// => auth succcess
//			return ResponseEntity.ok(resp);
//	}
	}
}
