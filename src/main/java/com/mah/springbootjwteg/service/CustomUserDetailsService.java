package com.mah.springbootjwteg.service;

import com.mah.springbootjwteg.domain.User;
import com.mah.springbootjwteg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userByUsername = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(userByUsername.getUsername(), userByUsername.getPassword(), new ArrayList<>());
    }

}