package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RestController;

import com.iff.NexusBlog.models.Category;
import com.iff.NexusBlog.service.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/categories")
public class CategoriesApiController {

  private CategoryService categoryService;

  @GetMapping
  public List<Category> getCategories(@RequestParam String param) {
    return this.categoryService.getAll();
  }
  
  @GetMapping("/author/{id}")
  public ResponseEntity<List<Map<String, String>>> getCategoryByPost(@PathVariable String post_id) {
    Map<String, String> comment1 = new HashMap<String, String>() {{
      put("name", "technology");
      put("id", "23");
    }};

    Map<String, String> comment2 = new HashMap<String, String>() {{
      put("name", "health");
      put("id", "23");
    }};

    List<Map<String, String>> comments = new ArrayList<>();

    comments.add(comment1);
    comments.add(comment2);
    
    return ResponseEntity.ok(comments);
  }

  @PostMapping
  public ResponseEntity<Map<String, String>> createCategory(@RequestBody Map<String, Object> category) {
    String name = (String) category.get("name");
    
    Map<String, String> newCategory = new HashMap<String, String>(){{
      put("name", name);
      put("id", "1");
    }};

    return ResponseEntity.ok(newCategory);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Map<String,String>> updateCategory(@RequestBody Map<String, Object> category) {

    Map<String, String> foundUser = new HashMap<String, String>(){{
      put("city", "london");
      put("email", "lucas@email.com");
      put("name", "lucas");
      put("id", "10");
    }};

    foundUser.put("city", (String) category.get("city"));
    foundUser.put("email", (String) category.get("email"));
    foundUser.put("name", (String) category.get("name"));
    
    return ResponseEntity.ok(foundUser);
  }
  
  

  @DeleteMapping("/{id}")
  public ResponseEntity<String> destroyCategory(@PathVariable String id){
    return ResponseEntity.ok(String.format("Category with id %s Deleted", id));
  }
  
}
