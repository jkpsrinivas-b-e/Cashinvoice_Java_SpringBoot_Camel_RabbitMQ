package com.example.cashinvoice.controller;

import com.example.cashinvoice.config.JwtUtil;
import com.example.cashinvoice.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {

        System.out.println("AuthController login called, username=" + request.getUsername());

        try {
            String token;

            if ("admin".equals(request.getUsername())) {
                token = jwtUtil.generateToken("admin", "ADMIN");
            } else {
                token = jwtUtil.generateToken(request.getUsername(), "USER");
            }

            return ResponseEntity.ok(token);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("LOGIN FAILED: " + e.getClass().getName() + " : " + e.getMessage());
        }
    }
}
