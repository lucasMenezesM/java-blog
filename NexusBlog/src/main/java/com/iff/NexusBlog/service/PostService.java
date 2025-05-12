package com.iff.NexusBlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.repository.PostRepository;
import com.iff.NexusBlog.repository.UserRepository;

@Service
public class PostService {

  @Autowired
  private final PostRepository postRepository;

  @Autowired 
  final UserRepository userRepository;

  @Autowired
  private UserService userService;

  public PostService(PostRepository postRepository, UserRepository userRepository, UserService userService) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.userService = userService;
  }

  public List<Post> getAll(){
    return this.postRepository.findAllByOrderByCreatedAtDesc();
  }

  public Post getById(Long id) {
    return this.postRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
  }

  public Post create(Post post, Long user_id) {
    User user = this.userService.getById(user_id);
    post.setUser(user);

    return this.postRepository.save(post);
  }

  public Post update(Post postToUpdate) {
    
    return this.postRepository.save(postToUpdate);
  }

  public void delete(Long id) {
    Post post = this.getById(id);
    this.postRepository.delete(post);
  }

  public List<Post> getPostsByUserId(Long id){
    return this.postRepository.getPostsByUserId(id);
  }

}
