package com.example.JournalApplication.service;


import com.example.JournalApplication.entity.User;
import com.example.JournalApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        String redisKey = "user:" + userId;

        // Pehle Redis Cache Check Karo
        User user = (User) redisTemplate.opsForValue().get(redisKey);
        if (user != null) {
            System.out.println("Data from Redis Cache");
            return user;
        }

        // Agar Redis Me Nahi Mila Toh Database Se Fetch Karo
        user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Redis Me Cache Karo (10 Minutes Ke Liye)
            redisTemplate.opsForValue().set(redisKey, user, 10, TimeUnit.MINUTES);
        }

        return user;
    }
}

