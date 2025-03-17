package com.iff.NexusBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.NexusBlog.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
  
}
