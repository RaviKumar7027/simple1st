package com.example.JournalApplication.controller;

import com.example.JournalApplication.entity.JournalEntity;
import com.example.JournalApplication.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class HomeController {

    @Autowired
    private JournalService journalService;

    @GetMapping("/h")
    public String sayHello() {
        return "hello my favourite spring boot";
    }

    // ✅ Get All Journal Entries
    @GetMapping("/abc")
    public ResponseEntity<List<JournalEntity>> getAll() {
        return ResponseEntity.ok(journalService.getAllEntries());
    }

    // ✅ Get Journal Entry By ID
    @GetMapping("/{id}")
    public ResponseEntity<JournalEntity> getJournalById(@PathVariable Long id) {
        return ResponseEntity.ok(journalService.getJournalById(id));
    }

    // ✅ Create New Journal Entry
    @PostMapping("/p")
    public ResponseEntity<String> createEntry(@RequestBody JournalEntity newEntry) {
        journalService.saveEntry(newEntry);
        return ResponseEntity.status(HttpStatus.CREATED).body("Journal Entry Successfully Created!");
    }

    // ✅ Update Journal Entry By ID
    @PutMapping("/update/{id}")
    public ResponseEntity<JournalEntity> updateJournal(@PathVariable Long id, @RequestBody JournalEntity updatedJournal) {
        return ResponseEntity.ok(journalService.updateJournalEntry(id, updatedJournal));
    }

    // ✅ Delete Journal Entry By ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteJournal(@PathVariable Long id) {
        journalService.deleteJournalById(id);
        return ResponseEntity.ok("Journal Entry Deleted Successfully!");
    }
}
