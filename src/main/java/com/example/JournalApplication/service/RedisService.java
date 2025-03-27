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

    public User getUserById(Long id) {
        String redisKey = "user:" + id;

        // Pehle Redis Cache Check Karo
        User user = (User) redisTemplate.opsForValue().get(redisKey);
        if (user != null) {
            System.out.println("Data from Redis Cache");
            return user;
        }

        // Agar Redis Me Nahi Mila Toh Database Se Fetch Karo
        user = userRepository.findById(id).orElse(null);
        if (user != null) {
            // Redis Me Cache Karo (10 Minutes Ke Liye)
            saveUserToCache(user);
        }

        return user;
    }

    public void saveUserToCache(User user) {
        String redisKey = "user:" + user.getId();
        redisTemplate.opsForValue().set(redisKey, user, 10, TimeUnit.MINUTES);
    }

    public void deleteUserFromCache(Long id) {
        String redisKey = "user:" + id;
        redisTemplate.delete(redisKey);
    }
}
