package com.example.JournalApplication.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j  // Lombok will automatically create a logger
@Service
public class LogService {

    public void process() {
        log.info("Processing started...");
        log.debug("Debugging details here...");
        log.warn("Warning message...");
        log.error("Error occurred!");
    }
}

