package com.iff.NexusBlog.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iff.NexusBlog.models.Category;

public class CategoryDTO {
  private Long id;
  private String name;
  private LocalDate created_at;
  private LocalDate updated_at;
  private List<PostDTO> posts;

  public CategoryDTO(){}

  public CategoryDTO(Category category) {
    this.id = category.getId();
    this.name = category.getName();
    this.created_at = category.getCreatedAt();
    this.updated_at = category.getUpdatedAt();
    
    if(category.getPosts() == null){
      this.posts = new ArrayList<>();
    }else{
      this.posts = category.getPosts().stream()
                            .map(PostDTO::new)
                            .collect(Collectors.toList());
    }
  }

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

  public List<PostDTO> getPosts() {
    return posts;
  }

  public void setPosts(List<PostDTO> posts) {
    this.posts = posts;
  }

  
}
