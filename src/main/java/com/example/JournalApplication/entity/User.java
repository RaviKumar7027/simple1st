package com.example.JournalApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;
import org.springframework.stereotype.Indexed;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 @Column(unique = true,nullable = false)//alg alg users honge or null nhi rhega column
    private String username;
 @Column(nullable = false)//khali nhi rhega column
    private String password;

    @Version  // ✅ Optimistic Locking ke liye versioning enable kar raha hai
    private int version;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // ✅ Yeh Circular Dependency aur Lazy Loading issue fix karega
    private List<JournalEntity> journalEntities=new ArrayList<>();
}
