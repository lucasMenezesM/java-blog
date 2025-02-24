package com.iff.NexusBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeViewController {

  @GetMapping("/home")
  public String Home() {
      return "home";
  }
}
