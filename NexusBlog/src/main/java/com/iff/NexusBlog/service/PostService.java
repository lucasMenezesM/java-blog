package com.iff.NexusBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.repository.PostRepository;

public class PostService {

  @Autowired
  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getAll(){
    return this.postRepository.findAll();
  }

  public Post getById(Long id) {
    return this.postRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
  }

  public Post create(Post post) {
    return this.postRepository.save(post);
  }

  public Post update(Long id, Post updatedPost) {
    Post existingPost = this.getById(id);
    existingPost.setTitle(updatedPost.getTitle());
    existingPost.setBody(updatedPost.getBody());
    // Update other fields as necessary
    return this.postRepository.save(existingPost);
  }

  public void delete(Long id) {
    Post post = this.getById(id);
    this.postRepository.delete(post);
  }
}
