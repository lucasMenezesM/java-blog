package com.iff.NexusBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.NexusBlog.models.CommonUser;

public interface CommonUserRepository extends JpaRepository<CommonUser, Long> {
  
}
