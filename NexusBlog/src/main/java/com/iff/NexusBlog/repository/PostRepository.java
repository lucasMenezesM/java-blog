package com.iff.NexusBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iff.NexusBlog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByTitleContainingIgnoreCase(String title);
  List<Post> findByBodyContainingIgnoreCase(String body);

  @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.body) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  List<Post> searchByKeyword(@Param("keyword") String keyword);

}
