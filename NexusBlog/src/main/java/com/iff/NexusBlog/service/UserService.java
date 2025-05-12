package com.iff.NexusBlog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iff.NexusBlog.exception.ResourceAlreadyExistsException;
import com.iff.NexusBlog.exception.ResourceNotFoundException;
import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.repository.UserRepository;

@Service
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
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }

  public User create(User user) {
    List<User> userFound = userRepositoryRepository.findByEmailContainingIgnoreCase(user.getEmail());
    if(!userFound.isEmpty())
      throw new ResourceAlreadyExistsException("Email already registered");

    user.setCreatedAt(java.time.LocalDate.now());
    user.setUpdatedAt(java.time.LocalDate.now());
    user.setLastActivity(java.time.LocalDate.now());
    
    return this.userRepositoryRepository.save(user);
  }

  public User update(Long id, User userDetails) {
    User user = this.userRepositoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        
    user.setFirstName(userDetails.getFirstName());
    user.setLastName(userDetails.getLastName());
    user.setBio(userDetails.getBio());
    user.setEmail(userDetails.getEmail());
    user.setBirthday(userDetails.getBirthday());
    if (!userDetails.getPassword().isEmpty())
      user.setPassword(userDetails.getPassword());

    user.setUpdatedAt(java.time.LocalDate.now());

    try {
      return this.userRepositoryRepository.save(user);
  } catch (Exception e) {
      e.printStackTrace(); // Ou logue com um logger
      throw e;
  }

    // return this.userRepositoryRepository.save(user);
  }

  public void delete(Long id) {
    User user = this.userRepositoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    this.userRepositoryRepository.delete(user);
  }
}
