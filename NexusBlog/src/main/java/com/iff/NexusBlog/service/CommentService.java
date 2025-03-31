package com.iff.NexusBlog.service;

import java.util.List;

import com.iff.NexusBlog.models.Comment;
import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.repository.CommentRepository;

public class CommentService {
  private final CommentRepository commentRepository;
  private final PostService postService;

  public CommentService(CommentRepository commentRepository, PostService postService){
    this.commentRepository = commentRepository;
    this.postService = postService;
  }

  public List<Comment> getAll(){
    return this.commentRepository.findAll();
  }

  public Comment getById(Long id) {
    return this.commentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
  }

  public Comment create(Comment comment, long post_id) {

    Post post = this.postService.getById(post_id);
    comment.setPost(post);

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
