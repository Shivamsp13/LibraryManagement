package com.library_management.LibraryManagement.service;

import com.library_management.LibraryManagement.DTO.LoginRequestDTO;
import com.library_management.LibraryManagement.DTO.LoginResponseDTO;
import com.library_management.LibraryManagement.DTO.RegisterRequestDTO;
import com.library_management.LibraryManagement.JWT.JwtService;
import com.library_management.LibraryManagement.entity.User;
import com.library_management.LibraryManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User Already Registered");
        }

        Set<String> roles=new HashSet<String>();
        roles.add("ROLE_USER");

        User user=new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO) {
        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User Already Registered");
        }

        Set<String> roles=new HashSet<String>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user=new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword())
        );
        User user=userRepository.findByUsername(loginRequestDTO.getUsername()).
                orElseThrow(()-> new RuntimeException("This Username does not exist"));

        String token=jwtService.generateToken(user);

        return LoginResponseDTO.builder().Token(token)
                .Username(user.getUsername())
                .Roles(user.getRoles())
                .build();
    }
//    private String Username;
//    private String Email;
//    private String Password;

}
