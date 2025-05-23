package com.iff.NexusBlog.models;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

// @JsonIdentityInfo(
//   generator = ObjectIdGenerators.PropertyGenerator.class,
//   property = "id"
// )
@Entity
@Table(name = "comments")
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonBackReference("user-comment")
  private User user;

  @ManyToOne
  @JoinColumn(name = "post_id", nullable = false)
  // @JsonBackReference("post-comment")
  private Post post;

  @NotBlank(message = "O conteúdo do comentário não pode estar vazio.")
  @Column(nullable = false)
  private String content;

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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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
