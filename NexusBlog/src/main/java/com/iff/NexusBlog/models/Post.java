package com.iff.NexusBlog.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class,
//   property = "id"
// )
@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonBackReference("user-post")
  private User user;

  @ManyToMany
  @JoinTable(
      name = "posts_categories",
      joinColumns = @JoinColumn(name = "post_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  // @JsonManagedReference("post-category")
  private Set<Category> categories = new HashSet<>();

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  // @JsonManagedReference("post-comment")
  private List<Comment> comments;

  @Size(min = 3, message = "O título do Post deve possuir no mínimo 3 caracteres.")
  @Column(nullable = false)
  private String title;
  
  @Size(min = 5, message = "O corpo do Post deve possuir no mínimo 5 caracteres.")
  @Column(nullable = false)
  private String body;

  @Column() 
  private LocalDate createdAt;

  @Column() 
  private LocalDate updatedAt;

  public Post(){
  }

  public Post(User user, Set<Category> categories, String title, String body) {
    this.user = user;
    this.categories = categories != null ? null : categories;
    this.title = title;
    this.body = body;
    this.createdAt = LocalDate.now();
    this.updatedAt = LocalDate.now();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Category> getCategories() {
    return categories;
  }

  public void setCategories(Set<Category> categories) {
    this.categories = categories;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
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
