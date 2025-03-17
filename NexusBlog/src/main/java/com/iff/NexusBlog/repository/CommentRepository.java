package com.iff.NexusBlog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.NexusBlog.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  
}
