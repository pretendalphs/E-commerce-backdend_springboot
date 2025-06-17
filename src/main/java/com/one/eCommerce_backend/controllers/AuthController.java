package com.one.eCommerce_backend.controllers;

import com.one.eCommerce_backend.dtos.UserLogin;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {


    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody UserLogin userLogin
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(401).build();
    }
}
