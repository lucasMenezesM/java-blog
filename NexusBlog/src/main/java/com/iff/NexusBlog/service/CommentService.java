package com.iff.NexusBlog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iff.NexusBlog.exception.ResourceNotFoundException;
import com.iff.NexusBlog.models.Comment;
import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.repository.CommentRepository;

@Service
public class CommentService {
  private final CommentRepository commentRepository;
  private final PostService postService;
  private final UserService userService;

  public CommentService(CommentRepository commentRepository, PostService postService, UserService userService){
    this.commentRepository = commentRepository;
    this.postService = postService;
    this.userService = userService;
  }

  public List<Comment> getAll(){
    return this.commentRepository.findAll();
  }

  public Comment getById(Long id) {
    return this.commentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
  }

  public List<Comment> getCommentsByUser(Long id){
    List<Comment> comments = this.commentRepository.findByUserId(id);
    if (comments.isEmpty()) {
        throw new ResourceNotFoundException("Comments not found");
    }
    return comments;
  }

  public List<Comment> getCommentsByPost(Long post_id){
    List<Comment> comments = this.commentRepository.findByPostId(post_id);
    if (comments.isEmpty()) {
        throw new ResourceNotFoundException("Comments not found");
    }
    return comments;
  }

  public Comment create(Comment comment, Long post_id, Long user_id) {
    Post post = this.postService.getById(post_id);
    User user = this.userService.getById(user_id);

    comment.setPost(post);
    comment.setUser(user);
    
    comment.setCreatedAt(java.time.LocalDate.now());
    comment.setUpdatedAt(java.time.LocalDate.now());

    return this.commentRepository.save(comment);
  }

  public Comment update(Long id, Comment updatedComment) {
    Comment existingComment = this.getById(id);
    existingComment.setContent(updatedComment.getContent());
    existingComment.setUser(updatedComment.getUser());
    return this.commentRepository.save(existingComment);
  }

  public void delete(Long id) {
    Comment existingComment = this.getById(id);
    this.commentRepository.delete(existingComment);
  }
}
