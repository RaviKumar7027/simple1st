package com.example.JournalApplication.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name ="journal_entity")
public class JournalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user; //user class ka reference hai

    @Version  // ✅ Hibernate ko version tracking dene ke liye
    @Column(columnDefinition = "int default 0")
    private Integer version = 0;  // ✅ Default value 0 de diya

}
