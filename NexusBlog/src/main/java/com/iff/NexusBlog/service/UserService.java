package com.iff.NexusBlog.service;

import java.util.List;

import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.repository.UserRepository;

public class UserService {
  private final UserRepository userRepositoryRepository;

  public UserService(UserRepository userRepositoryRepository) {
    this.userRepositoryRepository = userRepositoryRepository;
  }
  
  public List<User> getAll(){
    return this.userRepositoryRepository.findAll();
  }

  public User getById(Long id) {
    return this.userRepositoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
  }

  public User create(User user) {
    return this.userRepositoryRepository.save(user);
  }

  public User update(Long id, User userDetails) {
    User user = this.userRepositoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    user.setName(userDetails.getName());
    user.setEmail(userDetails.getEmail());
    // Update other fields as needed
    return this.userRepositoryRepository.save(user);
  }

  public void delete(Long id) {
    User user = this.userRepositoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    this.userRepositoryRepository.delete(user);
  }
}
