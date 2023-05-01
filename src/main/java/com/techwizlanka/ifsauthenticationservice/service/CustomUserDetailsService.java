package com.techwizlanka.ifsauthenticationservice.service;

import com.techwizlanka.ifsauthenticationservice.config.CustomUserDetails;
import com.techwizlanka.ifsauthenticationservice.entity.UserCredential;
import com.techwizlanka.ifsauthenticationservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> credential = userCredentialRepository.findByName(username);
        return credential.map(CustomUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found with name: " + username));
    }
}
