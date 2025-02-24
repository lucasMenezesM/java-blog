package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/comments")
public class CommentsApiController {
  @GetMapping
  public ResponseEntity<List<Map<String, String>>> getComments(@RequestParam String param) {
    Map<String, String> comment1 = new HashMap<String, String>() {{
      put("author_id", "32");
      put("post_id", "25");
      put("id", "23");
      put("content", "comment content");
    }};

    Map<String, String> comment2 = new HashMap<String, String>() {{
      put("author_id", "342");
      put("post_id", "251");
      put("id", "213");
      put("content", "comment 2 content");
    }};

    List<Map<String, String>> comments = new ArrayList<>();

    comments.add(comment1);
    comments.add(comment2);
    
    return ResponseEntity.ok(comments);
  }
  
  @GetMapping("/author/{id}")
  public ResponseEntity<List<Map<String, String>>> getCommentByAuthor(@PathVariable String id) {
    Map<String, String> comment1 = new HashMap<String, String>() {{
      put("author_id", "32");
      put("post_id", "25");
      put("id", "23");
      put("content", "comment 2 content");
    }};

    Map<String, String> comment2 = new HashMap<String, String>() {{
      put("author_id", "132");
      put("post_id", "205");
      put("id", "232");
      put("content", "comment content");
    }};

    List<Map<String, String>> comments = new ArrayList<>();

    comments.add(comment1);
    comments.add(comment2);
    
    return ResponseEntity.ok(comments);
  }

  @PostMapping
  public ResponseEntity<Map<String, String>> createComment(@RequestBody Map<String, Object> comment) {
    String content = (String) comment.get("content");
    String author_id = (String) comment.get("author_id");
    String post_id = (String) comment.get("post_id");
    
    Map<String, String> newComment = new HashMap<String, String>(){{
      put("content", content);
      put("post_id", post_id);
      put("author_id", author_id);
    }};

    return ResponseEntity.ok(newComment);
  }
  

  @DeleteMapping("/{id}")
  public ResponseEntity<String> destroyComment(@PathVariable String id){
    return ResponseEntity.ok("Comment Deleted");
  }
  
}
