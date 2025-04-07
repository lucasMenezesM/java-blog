package com.iff.NexusBlog.models;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class,
//   property = "id"
// )
@Entity
@Table(name = "categories")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Size(min = 3, message = "O nome da Categoria deve possuir no mínimo 3 caracteres.")
  @Column(nullable = false)
  private String name;

  @ManyToMany(mappedBy = "categories")
  // @JsonBackReference("post-category")
  private Set<Post> posts = new HashSet<>();

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<Post> getPosts() {
    return posts;
  }

  public void setPosts(Set<Post> posts) {
    this.posts = posts;
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
