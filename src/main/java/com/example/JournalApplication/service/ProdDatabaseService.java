package com.example.JournalApplication.service;
import jakarta.annotation.PostConstruct;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdDatabaseService {

    @PostConstruct
    public void init() {
        System.out.println("✅ ProdDatabaseService Loaded! Profile: PROD");
    }

    public void find() {
        System.out.println("hello production base databases ");
    }
}
