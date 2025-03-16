package com.iff.NexusBlog.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    private boolean active;

    @Column(nullable = false) 
    private String name;
    
    @Column(nullable = false) 
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Column() 
    private String bio;

    @Column(nullable = false) 
    private LocalDate birthday;

    @Column(nullable = false) 
    private LocalDate lastActivity;

    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false) 
    private LocalDate createdAt;

    @Column(nullable = false) 
    private LocalDate updatedAt;
}
