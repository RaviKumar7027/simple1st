
package com.example.JournalApplication.service;

import com.example.JournalApplication.entity.JournalEntity;
import com.example.JournalApplication.entity.User;
import com.example.JournalApplication.repository.JournalRepository;
import com.example.JournalApplication.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Transactional
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    public void saveEntry(JournalEntity journalEntity) {
        User user = userRepository.findById(journalEntity.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User ID " + journalEntity.getUser().getId() + " not found."));
        journalEntity.setUser(user);
        journalRepository.save(journalEntity);
    }

    public List<JournalEntity> getAllEntries() {
        return journalRepository.findAll();
    }

    public JournalEntity getJournalById(Long id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal ID " + id + " not found."));
    }

    public JournalEntity updateJournalEntry(Long id, JournalEntity updatedJournal) {
        JournalEntity journal = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal ID " + id + " not found."));

        journal.setTitle(updatedJournal.getTitle());
        journal.setContent(updatedJournal.getContent());

        User user = userRepository.findById(updatedJournal.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User ID " + updatedJournal.getUser().getId() + " not found."));
        entityManager.refresh(user);
        journal.setUser(user);

        return journalRepository.save(journal);
    }

    public void deleteJournalById(long id) {
        JournalEntity journal = journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Journal ID " + id + " not found."));
        journalRepository.delete(journal);
    }
}
