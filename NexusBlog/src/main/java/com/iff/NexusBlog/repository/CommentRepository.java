package com.iff.NexusBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iff.NexusBlog.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  List<Comment> findByUserId(Long userId);
  List<Comment> findByPostId(Long userId);
}
