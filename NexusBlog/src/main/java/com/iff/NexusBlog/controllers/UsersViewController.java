package com.iff.NexusBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class UsersViewController {
  private UserService userService;
  
  public UsersViewController(UserService userService) {
      this.userService = userService;
  }

  @GetMapping("users/{id}")
  public String getUserProfile(@PathVariable Long id, Model model) {
    User user = userService.getById(id);
    model.addAttribute("user", user);
    return "users/profile";
  }

  @GetMapping("users")
  public String getAllUsers(Model model) {
    model.addAttribute("users", userService.getAll());
    return "users/users";
  }

  @GetMapping("users/edit/{id}")
  public String editUser(@PathVariable Long id, Model model) {
    User user = userService.getById(id);
    model.addAttribute("user", user);
    return "users/editUser";
  }

  @PostMapping("users/edit/{id}")
  public String updateUser(@PathVariable Long id, @ModelAttribute User user) {
    userService.update(id, user);
    return "redirect:/users/" + id;
  }

  @PostMapping("users")
  public String createUser(@ModelAttribute User user) {
    userService.create(user);
    return "redirect:/users";
  }

  @GetMapping("/users/delete/{id}")
  public String deleteUser(@PathVariable Long id) {
      userService.delete(id);
      return "redirect:/users";
  }
  
  
}
