package com.iff.NexusBlog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iff.NexusBlog.models.Category;

import com.iff.NexusBlog.repository.CategoryRepository;

@Service
public class CategoryService {
  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  public List<Category> getAll(){
    return this.categoryRepository.findAll();
  }

  public Category getById(Long id) {
    return this.categoryRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
  }

  public Category create(Category category) {
    category.setCreatedAt(java.time.LocalDate.now());
    category.setUpdatedAt(java.time.LocalDate.now());
    
    return this.categoryRepository.save(category);
  }

  public Category update(Long id, Category updatedCategory) {
    Category existingCategory = this.getById(id);
    existingCategory.setName(updatedCategory.getName());
    return this.categoryRepository.save(existingCategory);
  }

  public void delete(Long id) {
    Category category = this.getById(id);
    this.categoryRepository.delete(category);
  }
}
