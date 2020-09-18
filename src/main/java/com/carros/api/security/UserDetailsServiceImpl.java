package com.carros.api.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.carros.domain.User;
import com.carros.domain.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByLogin(username);
		if(user == null) {
			throw new UsernameNotFoundException("user not found");
		}
		
		return user;
		
		
	}

}
