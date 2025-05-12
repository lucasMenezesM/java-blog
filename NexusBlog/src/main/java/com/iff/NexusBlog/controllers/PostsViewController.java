package com.iff.NexusBlog.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.PatchExchange;

import com.iff.NexusBlog.dto.PostDTO;
import com.iff.NexusBlog.models.Comment;
import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.service.CommentService;
import com.iff.NexusBlog.service.PostService;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class PostsViewController {

  private PostService postService;
  private CommentService commentService;

  public PostsViewController(PostService postService, CommentService commentService) {
      this.postService = postService;
      this.commentService = commentService;
  }
  // Para acessar a página
  @GetMapping("posts/new")
  public String NewPost() {
      return "posts/NewPost";
  }

  // Para criar o post
  @PostMapping("posts/new")
  public String createPost(@ModelAttribute Post post) {
    post.setCreatedAt(java.time.LocalDate.now());
    post.setUpdatedAt(java.time.LocalDate.now());
    postService.create(post, 6L);
    
    return "redirect:/home";
  }

  @GetMapping("posts/{id}")
  public String showPost(@PathVariable Long id, Model model) {
      Post post = postService.getById(id);
      List<Comment> comments = post.getComments();
      model.addAttribute("post", post);
      
      model.addAttribute("comments", comments);

      return "posts/post_details";
  }

  @GetMapping("posts/edit/{id}")
  public String editPost(@PathVariable Long id, Model model) {
      Post post = postService.getById(id);
      model.addAttribute("post", post);
      return "posts/editPost";
  }

  @PostMapping("posts/edit/{id}")
  public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
      Post existingPost = postService.getById(id);
      existingPost.setTitle(post.getTitle());
      existingPost.setBody(post.getBody());
      existingPost.setUpdatedAt(java.time.LocalDate.now());
      postService.update(existingPost);
      
      return "redirect:/posts/" + id;
  }

  @GetMapping("/posts/delete/{id}")
  public String deletePost(@PathVariable Long id) {
      postService.delete(id);
      return "redirect:/home";
  }


  
}
