package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.NexusBlog.dto.PostDTO;
import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.repository.CategoryRepository;
import com.iff.NexusBlog.repository.PostRepository;
import com.iff.NexusBlog.repository.UserRepository;
import com.iff.NexusBlog.response.MessageResponse;
import com.iff.NexusBlog.service.PostService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/posts")
public class PostsApiController {

  @Autowired
  PostService postService;

  @Autowired
  UserRepository userRepository;
  
  @Autowired
  CategoryRepository categoryRepository;
  
  @Autowired
  PostRepository postRepository;

  @GetMapping()
  public ResponseEntity<CollectionModel<EntityModel<PostDTO>>> getPosts() {
    List<Post> posts = postService.getAll();

    List<EntityModel<PostDTO>> postModels = posts.stream()
        .map(post -> {
          PostDTO dto = new PostDTO(post);
          return EntityModel.of(dto,
              linkTo(methodOn(PostsApiController.class).getPostById(post.getId())).withSelfRel(),
              linkTo(methodOn(PostsApiController.class).getUserByPost(post.getUser().getId())).withRel("author-posts")
          );
        })
        .toList();

    CollectionModel<EntityModel<PostDTO>> collectionModel = CollectionModel.of(postModels);
    collectionModel.add(linkTo(methodOn(PostsApiController.class).getPosts()).withSelfRel());

    return ResponseEntity.ok(collectionModel);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<PostDTO>> getPostById(@PathVariable Long id) {
    Post post = postService.getById(id);
  PostDTO dto = new PostDTO(post);

  EntityModel<PostDTO> postModel = EntityModel.of(dto,
      linkTo(methodOn(PostsApiController.class).getPostById(id)).withSelfRel(),
      linkTo(methodOn(PostsApiController.class).getUserByPost(post.getUser().getId())).withRel("author-posts"),
      linkTo(methodOn(PostsApiController.class).getPosts()).withRel("all-posts")
  );

  return ResponseEntity.ok(postModel);
  }
  
  @GetMapping("/author/{id}")
  public ResponseEntity<CollectionModel<EntityModel<PostDTO>>> getUserByPost(@PathVariable Long id) {
    List<Post> posts = postService.getPostsByUserId(id);

    List<EntityModel<PostDTO>> postModels = posts.stream()
        .map(post -> {
          PostDTO dto = new PostDTO(post);
          return EntityModel.of(dto,
              linkTo(methodOn(PostsApiController.class).getPostById(post.getId())).withRel("self"),
              linkTo(methodOn(PostsApiController.class).getPosts()).withRel("all-posts")
          );
        })
        .toList();
  
    CollectionModel<EntityModel<PostDTO>> collectionModel = CollectionModel.of(postModels);
    collectionModel.add(linkTo(methodOn(PostsApiController.class).getUserByPost(id)).withSelfRel());
  
    return ResponseEntity.ok(collectionModel);
  }
  
  @PostMapping
  public ResponseEntity<EntityModel<PostDTO>> CreatePost(@ModelAttribute PostDTO postData) {
    Post newPost = new Post();
    newPost.setTitle(postData.getTitle());
    newPost.setBody(postData.getBody());
  
    Post createdPost = postService.create(newPost, postData.getUser_id());
  
    PostDTO dto = new PostDTO(createdPost);
  
    EntityModel<PostDTO> postModel = EntityModel.of(dto,
        linkTo(methodOn(PostsApiController.class).getPostById(createdPost.getId())).withRel("self"),
        linkTo(methodOn(PostsApiController.class).getPosts()).withRel("all-posts"),
        linkTo(methodOn(PostsApiController.class).getUserByPost(createdPost.getUser().getId())).withRel("author-posts")
    );
  
    return ResponseEntity
        .created(linkTo(methodOn(PostsApiController.class).getPostById(createdPost.getId())).toUri())
        .body(postModel);
  }

  @PatchMapping(value = "/{id}", consumes = "application/json")
  public ResponseEntity<EntityModel<PostDTO>> updatePost(@PathVariable Long id, @RequestBody PostDTO postData) {
    Post postToUpdate = postService.getById(id);
    postToUpdate.setTitle(postData.getTitle());
    postToUpdate.setBody(postData.getBody());

    Post updatedPost = postService.update(postToUpdate);

    PostDTO dto = new PostDTO(updatedPost);

    EntityModel<PostDTO> postModel = EntityModel.of(dto,
        linkTo(methodOn(PostsApiController.class).getPostById(id)).withSelfRel(),
        linkTo(methodOn(PostsApiController.class).getUserByPost(updatedPost.getUser().getId())).withRel("author-posts"),
        linkTo(methodOn(PostsApiController.class).getPosts()).withRel("all-posts")
    );

    return ResponseEntity.ok(postModel);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<EntityModel<MessageResponse>> destroyPost(@PathVariable Long id) {
    postService.delete(id);

    MessageResponse message = new MessageResponse("Post Successfully deleted");

    EntityModel<MessageResponse> response = EntityModel.of(message,
        linkTo(methodOn(PostsApiController.class).getPosts()).withRel("all-posts")
    );

    return ResponseEntity.ok(response);
  }
  
}

