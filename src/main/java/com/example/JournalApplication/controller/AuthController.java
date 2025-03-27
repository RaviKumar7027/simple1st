package com.example.JournalApplication.controller;

import com.example.JournalApplication.util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//hmne yhha pr token bnaya hai jo hardcoded hai



import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

    @RestController
    @RequestMapping("/auth")
    public class AuthController {

        private final JwtUtil jwtUtil;

        public AuthController(JwtUtil jwtUtil) {
            this.jwtUtil = jwtUtil;
        }

        @PostMapping("/login")
        public Map<String, String> login(@RequestBody Map<String, String> request) {
            String username = request.get("username");
            String password = request.get("password");

            // Simple Hardcoded User Check (Database Nahi Use Kar Raha)
            if ("admin".equals(username) && "password".equals(password)) {
                String token = jwtUtil.generateToken(username);

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return response;
            } else {
                throw new RuntimeException("Invalid Credentials");
            }
        }
    }


