package com.iff.NexusBlog.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.models.User;

public class PostDTO {
  private Long id;
  private Long user_id;
  private String title;
  private String body;
  private Set<Long> categories;
  private LocalDate created_at;
  private LocalDate updated_at;
  private User user;

  public PostDTO(){

  }
  public PostDTO(Post post) {
    this.id = post.getId();
    this.user_id = post.getUser().getId();
    this.title = post.getTitle();
    this.body = post.getBody();
    this.created_at = post.getCreatedAt();
    this.updated_at = post.getUpdatedAt();
    this.categories = post.getCategories().stream()
                          .map(category -> category.getId())
                          .collect(Collectors.toSet());
  }
  
  public Long getUser_id() {
    return user_id;
  }
  public void setUser_id(Long user_id) {
    this.user_id = user_id;
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
  public Set<Long> getCategories() {
    return categories;
  }
  public void setCategories(Set<Long> categories) {
    this.categories = categories;
  }
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public LocalDate getCreated_at() {
    return created_at;
  }
  public void setCreated_at(LocalDate created_at) {
    this.created_at = created_at;
  }
  public LocalDate getUpdated_at() {
    return updated_at;
  }
  public void setUpdated_at(LocalDate updated_at) {
    this.updated_at = updated_at;
  }
  public User getUser() {
    return user;
  }
  public void setUser(User user) {
    this.user = user;
  }

}
