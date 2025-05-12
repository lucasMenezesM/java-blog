package com.iff.NexusBlog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.iff.NexusBlog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
  List<Post> findByTitleContainingIgnoreCase(String title);
  List<Post> findByBodyContainingIgnoreCase(String body);
  List<Post> findAllByOrderByCreatedAtDesc();

  @Query("SELECT p.user FROM Post p WHERE p.id = :postId")
  Object getUserByPostId(@Param("postId") Long postId);

  @Query("SELECT p FROM Post p WHERE p.user.id = :userId")
  List<Post> getPostsByUserId(@Param("userId") Long userId);

  @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.body) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  List<Post> searchByKeyword(@Param("keyword") String keyword);

}
