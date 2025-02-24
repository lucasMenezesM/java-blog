package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UsersApiController {
  
  @GetMapping
  public ResponseEntity<List<Map<String,String>>> getUsers() {
    List<Map<String,String>> users = new ArrayList<>();

    Map<String, String> user1 = new HashMap<>();
    user1.put("id", "23");
    user1.put("email", "lucas");
    user1.put("city", "london");

    users.add(user1);

    Map<String, String> user2 = new HashMap<>();
    user2.put("id", "56");
    user2.put("email", "robert");
    user2.put("city", "barcelona");

    users.add(user2);

    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Map<String, String>> getUserById(@PathVariable String id) {
    Map<String, String> foundUser = new HashMap<String, String>() {{
      put("email", "lucas@email.com");
      put("name", "lucas");
      put("id", id);
    }};

    return ResponseEntity.ok(foundUser);
  }
  
  @PostMapping
  public ResponseEntity<Map<String,String>> createUser(@RequestBody Map<String, Object> user) {
    String name = (String) user.get("name");
    String email = (String) user.get("email");
    String city = (String) user.get("city");

    Map<String, String> newUser = new HashMap<>();
    newUser.put("city", city);
    newUser.put("email", email);
    newUser.put("name", name);
    
    return ResponseEntity.ok(newUser);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Map<String,String>> updateUser(@PathVariable String id) {

    Map<String, String> foundUser = new HashMap<String, String>(){{
      put("city", "london");
      put("email", "lucas@email.com");
      put("name", "lucas");
      put("id", id);
    }};
    
    return ResponseEntity.ok(foundUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> destroyUser(@PathVariable String id){
    return ResponseEntity.ok("Deleted");
  }
}