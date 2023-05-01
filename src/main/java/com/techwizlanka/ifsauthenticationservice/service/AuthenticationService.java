package com.techwizlanka.ifsauthenticationservice.service;

import com.techwizlanka.ifsauthenticationservice.entity.UserCredential;
import com.techwizlanka.ifsauthenticationservice.repository.UserCredentialRepository;
import com.techwizlanka.ifsauthenticationservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticationService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        userCredentialRepository.save(credential);
        return "User added to the db.";
    }

    public String generateToken(String username) throws IOException {
        return jwtUtil.generateToken(username);
    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }
}
