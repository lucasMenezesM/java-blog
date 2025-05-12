package com.iff.NexusBlog.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iff.NexusBlog.service.CategoryService;

import org.springframework.ui.Model;


import com.iff.NexusBlog.models.Category;


@Controller
public class CategoriesViewController {

  private CategoryService categoryService;

  public CategoriesViewController(CategoryService categoryService) {
      this.categoryService = categoryService;
  }

  @GetMapping("/categories")
  public String getCategories(Model model) {
      List<Category> categories = categoryService.getAll();
      model.addAttribute("categories", categories);
      return "categories/categories";
  }

  @GetMapping("/categories/{id}")
  public String showCategory(@PathVariable Long id, Model model) {
      Category category = categoryService.getById(id);
      model.addAttribute("category", category);
      return "categories/categoryDetails";
  }

  @GetMapping("categories/new")
  public String NewCategory() {
      return "categories/newCategory";
  }

  @PostMapping("categories")
  public String createCategory(@ModelAttribute Category category) {
    category.setCreatedAt(java.time.LocalDate.now());
    category.setUpdatedAt(java.time.LocalDate.now());
    categoryService.create(category);
    
    return "redirect:/categories";
  }

  @GetMapping("categories/edit/{id}")
  public String editCategory(@PathVariable Long id, Model model) {
      Category category = categoryService.getById(id);
      model.addAttribute("category", category);
      return "categories/editCategory";
  }

  @PostMapping("categories/edit/{id}")
  public String updateCategory(@PathVariable Long id, @ModelAttribute Category category) {
      categoryService.update(id, category);
      return "redirect:/categories/" + id;
  }

  @GetMapping("categories/delete/{id}")
  public String deleteCategory(@PathVariable Long id) {
      categoryService.delete(id);
      return "redirect:/categories";
  }
  
}
