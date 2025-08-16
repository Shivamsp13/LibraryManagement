package com.library_management.LibraryManagement.controller;


import com.library_management.LibraryManagement.DTO.LoginRequestDTO;
import com.library_management.LibraryManagement.DTO.LoginResponseDTO;
import com.library_management.LibraryManagement.DTO.RegisterRequestDTO;
import com.library_management.LibraryManagement.entity.User;
import com.library_management.LibraryManagement.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationservice;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        System.out.println("Inside registerNormalUser controller!");

        return ResponseEntity.ok(authenticationservice.registerNormalUser(registerRequestDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationservice.login(loginRequestDTO));
    }

}
