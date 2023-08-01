package com.zeta.springboot.jwtauth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zeta.springboot.jwtauth.config.UserInfoUserDetails;
import com.zeta.springboot.jwtauth.entity.UserInfo;
import com.zeta.springboot.jwtauth.repository.UserInfoRepository;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserInfoRepository repository;
	
	
//	Load a user by username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		UserInfo should be converted to UserDetails
//		To do that create a class of UserDetails
		 Optional<UserInfo> userInfo = repository.findByName(username);
		 return userInfo.map(UserInfoUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found "+username));
		
	}

}
