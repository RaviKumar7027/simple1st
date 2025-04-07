package com.example.JournalApplication.repository;

import com.example.JournalApplication.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface JournalRepository extends JpaRepository<JournalEntity,Long> {
    List<JournalEntity> findByUserId(Long userId);  // ✅ important

}
