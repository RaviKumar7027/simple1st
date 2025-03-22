package com.example.JournalApplication.controller;

import com.example.JournalApplication.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LogController implements CommandLineRunner {

    @Autowired
    private LogService logService;

    @Override
    public void run(String... args) {
        logService.process();  // LogService को call कर रहे हैं
    }
}
