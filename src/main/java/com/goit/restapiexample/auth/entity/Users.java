package com.goit.restapiexample.auth.entity;

import com.goit.restapiexample.note.Note;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    @OneToMany(mappedBy = "user")
    List<Note> notes;
}
