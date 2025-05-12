package com.iff.NexusBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationViewController {
  @GetMapping("/sign_in")
  public String sign_in() {
      return "sign_in";
  }
  
  @GetMapping("/sign_up")
  public String sign_up() {
      return "sign_up";
  }
  
}
