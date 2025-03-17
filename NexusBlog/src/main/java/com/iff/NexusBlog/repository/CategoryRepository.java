package com.iff.NexusBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.NexusBlog.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  
}
