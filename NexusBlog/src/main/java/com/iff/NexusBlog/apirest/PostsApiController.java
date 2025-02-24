package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/posts")
public class PostsApiController {

  @GetMapping
  public ResponseEntity<List<Map<String,String>>> getPosts() {
    List<Map<String,String>> lista = new ArrayList<>();

    Map<String, String> post1 = new HashMap<>();
    post1.put("id", "23");
    post1.put("author", "lucas");
    post1.put("content", "post content");

    lista.add(post1);

    Map<String, String> post2 = new HashMap<>();
    post2.put("id", "45");
    post2.put("author", "Robert");
    post2.put("content", "post2 content");

    lista.add(post2);

    return ResponseEntity.ok(lista);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, String>> getPostById(@PathVariable String id) {
    Map<String, String> post = new HashMap<>();
    post.put("id", id);
    post.put("author", "lucas");
    post.put("content", "post content");

    return ResponseEntity.ok(post);
  }
  
  @GetMapping("/author/{id}")
  public ResponseEntity<Map<String, String>> getPostByAuthorId(@PathVariable String id) {
    Map<String, String> post = new HashMap<>();
    post.put("id", "author id");
    post.put("author", "Author with id "+id);
    post.put("content", "post content");

    return ResponseEntity.ok(post);
  }
  
  @PostMapping
  public ResponseEntity<Map<String, String>> CreatePost(@RequestBody Map<String, Object> post) {
    String content = (String) post.get("content");
    String author = (String) post.get("author");
    
    Map<String, String> newPost = new HashMap<>();

    newPost.put("content", content);
    newPost.put("author", author);
    newPost.put("id", "324");
    
    return ResponseEntity.ok(newPost);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Map<String, String>> updatePost(@PathVariable String id, @RequestBody Map<String, Object> post) {
    String content = (String) post.get("content");
    String author = (String) post.get("author");
    
    Map<String, String> updatedPost = new HashMap<String, String>() {{
      put("content", content);
      put("author", author);
      put("id", id);
    }};

    updatedPost.put("content", content);
    updatedPost.put("author", author);
    
    return ResponseEntity.ok(updatedPost);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> destroyPost(@PathVariable String id, @RequestBody Map<String, Object> post){
    return ResponseEntity.ok("Deleted");
  }
  
}

