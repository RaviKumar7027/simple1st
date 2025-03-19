package com.example.JournalApplication.repository;

import com.example.JournalApplication.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepository extends JpaRepository<JournalEntity,Long> {



}
