package com.example.JournalApplication.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cron")
@Tag(name="CRON APIs")
public class CronJobController {

    @Scheduled(cron = "0 0 12 * * ?") // हर दिन 12 बजे execute होगा
    public void runTask() {
        System.out.println("Running Cron Job at " + System.currentTimeMillis());
    }

    @GetMapping("/runNow") // API से भी trigger कर सकते हैं
    public String runNow() {
        runTask();
        return "Cron Job executed manually!";
    }
}

