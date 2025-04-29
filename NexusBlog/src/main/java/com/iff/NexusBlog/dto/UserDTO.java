package com.iff.NexusBlog.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.iff.NexusBlog.models.User;

public class UserDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String password;
  private String email;
  private Boolean active;
  private List<CommentDTO> comments;  
  private List<PostDTO> posts;  
  private String bio;
  private LocalDate birthday;
  private LocalDate lastActivity;
  private LocalDate created_at;
  private LocalDate updated_at;

  public UserDTO(){}

  public UserDTO(User user) {
    this.id = user.getId();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.password = user.getPassword();
    this.email = user.getEmail();
    this.active = user.isActive();
    this.created_at = user.getCreatedAt();
    this.updated_at = user.getUpdatedAt();
     
    if(user.getPosts() == null){
      this.posts = new ArrayList<>();
    }else{
      this.posts = user.getPosts().stream()
                            .map(PostDTO::new)
                            .collect(Collectors.toList());
    }

    if(user.getComments() == null){
      this.comments = new ArrayList<>();
    }else{
      this.comments = user.getComments().stream()
                            .map(CommentDTO::new)
                            .collect(Collectors.toList());
    }

    this.bio = user.getBio();
    this.birthday = user.getBirthday();
    this.lastActivity = user.getLastActivity();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastname() {
    return lastName;
  }

  public void setLastname(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public List<CommentDTO> getComments() {
    return comments;
  }

  public void setComments(List<CommentDTO> comments) {
    this.comments = comments;
  }

  public List<PostDTO> getPosts() {
    return posts;
  }

  public void setPosts(List<PostDTO> posts) {
    this.posts = posts;
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

  

  
}
