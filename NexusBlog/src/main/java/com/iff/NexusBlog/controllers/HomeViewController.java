package com.iff.NexusBlog.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.iff.NexusBlog.apirest.PostsApiController;
import com.iff.NexusBlog.dto.PostDTO;
import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.service.PostService;
import com.iff.NexusBlog.service.UserService;


@Controller
public class HomeViewController {

  private final RestTemplate restTemplate = new RestTemplate();
  private final PostService postService;
  private final UserService userService;

  public HomeViewController(PostService postService, UserService userService) {
    this.postService = postService;
    this.userService = userService;
  }

  @GetMapping("/home")
  public String Home(Model model) {
    // Faz a chamada para a API de posts
    List<Post> posts = postService.getAll();
    
    // Envia a lista de posts para o Thymeleaf
    model.addAttribute("posts", posts);
    return "home";
  }
}
