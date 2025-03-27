package com.example.JournalApplication.controller;


//yha pr hmara token verify hoga shi ha ya glt
import com.example.JournalApplication.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/secure")
public class HelloController {

    private final JwtUtil jwtUtil;

    public HelloController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/hello")
    public String hello(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (jwtUtil.validateToken(token)) {
            return "Hello, " + jwtUtil.extractUsername(token);
        } else {
            return "Invalid or Expired Token";
        }
    }
}
