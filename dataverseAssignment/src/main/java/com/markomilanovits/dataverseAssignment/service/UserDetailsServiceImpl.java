package com.markomilanovits.dataverseAssignment.service;

import com.markomilanovits.dataverseAssignment.domain.User;
import com.markomilanovits.dataverseAssignment.model.UserLoginDetails;
import com.markomilanovits.dataverseAssignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserLoginDetails(user.getEmail(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}

