package com.iff.NexusBlog.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) 
    private boolean active;

    @NotBlank(message = "O primeiro nome não pode estar vazio.")
    @Column(nullable = false) 
    private String firstName;
    
    @NotBlank(message = "O sobrenome nome não pode estar vazio.")
    @Column(nullable = false) 
    private String lastName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Column() 
    private String bio;

    @NotBlank(message = "O data de nascimento não pode estar vazio.")
    @Past(message = "A data de nascimento deve estar no passado")
    @Column(nullable = false) 
    private LocalDate birthday;

    @Column(nullable = false) 
    private LocalDate lastActivity;

    @Email(message = "Formato de e-mail inválido")
    @Column(nullable = false, unique = true)
    private String email;
    
    @Min(value= 6, message = "A senha deve possuir no mínimo 6 caracteres")
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false) 
    private LocalDate createdAt;

    @Column(nullable = false) 
    private LocalDate updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(LocalDate lastActivity) {
        this.lastActivity = lastActivity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
