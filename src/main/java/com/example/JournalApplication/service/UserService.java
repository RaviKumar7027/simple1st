package com.example.JournalApplication.service;

import com.example.JournalApplication.entity.User;
import com.example.JournalApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisService redisService;

    public User saveUser(User user) {
        User savedUser = userRepository.save(user);

        // Redis me cache update karein
        redisService.saveUserToCache(savedUser);

        return savedUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return redisService.getUserById(id);
    }

    public User updateUser(Long id, User userDetails) {
        User existingUser = getUserById(id);
        existingUser.setUsername(userDetails.getUsername());
        existingUser.setPassword(userDetails.getPassword());

        User updatedUser = userRepository.save(existingUser);

        // Redis Cache ko update karo
        redisService.saveUserToCache(updatedUser);

        return updatedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);

        // Redis se user delete karein
        redisService.deleteUserFromCache(id);
    }
}
