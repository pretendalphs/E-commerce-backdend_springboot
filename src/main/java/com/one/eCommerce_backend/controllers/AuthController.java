package com.one.eCommerce_backend.controllers;

import com.one.eCommerce_backend.dtos.JwtResponse;
import com.one.eCommerce_backend.dtos.UserLogin;
import com.one.eCommerce_backend.services.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @RequestBody UserLogin userLogin
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getEmail(),
                        userLogin.getPassword()
                )
        );
        var token = jwtService.generateToken(userLogin.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(401).build();
    }
}
