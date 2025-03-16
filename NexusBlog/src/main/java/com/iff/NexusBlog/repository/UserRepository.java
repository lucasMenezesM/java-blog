package com.iff.NexusBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.NexusBlog.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
  
}
