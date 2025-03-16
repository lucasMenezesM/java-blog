package com.iff.NexusBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.NexusBlog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  
}
