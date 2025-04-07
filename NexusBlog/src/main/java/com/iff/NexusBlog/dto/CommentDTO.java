package com.iff.NexusBlog.dto;

import java.time.LocalDate;

import com.iff.NexusBlog.models.Comment;

public class CommentDTO {
  private Long id;
  private String content;
  private Long user_id;
  private Long post_id;
  private LocalDate created_at;
  private LocalDate updated_at;

  public CommentDTO() {
  }

  public CommentDTO(Comment comment) {
    this.id = comment.getId();
    this.content = comment.getContent();
    this.user_id = comment.getUser().getId();
    this.post_id = comment.getPost().getId();
    this.created_at = comment.getCreatedAt();
    this.updated_at = comment.getUpdatedAt();
  }

  

  public String getContent() {
    return content;
  }


  public void setContent(String content) {
    this.content = content;
  }


  public Long getUser_id() {
    return user_id;
  }


  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }


  public Long getPost_id() {
    return post_id;
  }


  public void setPost_id(Long post_id) {
    this.post_id = post_id;
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  

  
}

