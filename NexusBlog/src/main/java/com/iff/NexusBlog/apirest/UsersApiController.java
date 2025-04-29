package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iff.NexusBlog.dto.UserDTO;
import com.iff.NexusBlog.models.User;
import com.iff.NexusBlog.response.MessageResponse;
import com.iff.NexusBlog.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/users")
public class UsersApiController {
  
  @Autowired
  private UserService userService;
  
  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<UserDTO>>> getUsers() {
    List<User> users = userService.getAll();

    List<EntityModel<UserDTO>> userModels = users.stream()
        .map(user -> {
          UserDTO dto = new UserDTO(user);
          return EntityModel.of(dto,
              linkTo(methodOn(UsersApiController.class).getUserById(user.getId())).withRel("user-details"),
              linkTo(methodOn(UsersApiController.class).getUsers()).withSelfRel()
          );
        })
        .toList();

    CollectionModel<EntityModel<UserDTO>> collectionModel = CollectionModel.of(userModels);
    collectionModel.add(linkTo(methodOn(UsersApiController.class).getUsers()).withSelfRel());

    return ResponseEntity.ok(collectionModel);
  }

  @GetMapping("/{id}")
  public ResponseEntity<EntityModel<UserDTO>> getUserById(@PathVariable Long id) {
    User user = userService.getById(id);

    UserDTO dto = new UserDTO(user);
  
    EntityModel<UserDTO> userModel = EntityModel.of(dto,
        linkTo(methodOn(UsersApiController.class).getUserById(id)).withSelfRel(),
        linkTo(methodOn(UsersApiController.class).getUsers()).withRel("all-users")
    );
  
    return ResponseEntity.ok(userModel);
  }
  
  @PostMapping()
  public ResponseEntity<EntityModel<UserDTO>> createUser(@RequestBody @Valid User user) {
    User createdUser = userService.create(user);

    UserDTO dto = new UserDTO(createdUser);

    EntityModel<UserDTO> userModel = EntityModel.of(dto,
        linkTo(methodOn(UsersApiController.class).getUserById(createdUser.getId())).withSelfRel(),
        linkTo(methodOn(UsersApiController.class).getUsers()).withRel("all-users")
    );

    return ResponseEntity
        .created(linkTo(methodOn(UsersApiController.class).getUserById(createdUser.getId())).toUri())
        .body(userModel);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<EntityModel<UserDTO>> updateUser(@PathVariable Long id, @RequestBody User userData) {
    User updatedUser = userService.update(id, userData);

    UserDTO dto = new UserDTO(updatedUser);
  
    EntityModel<UserDTO> userModel = EntityModel.of(dto,
        linkTo(methodOn(UsersApiController.class).getUserById(updatedUser.getId())).withSelfRel(),
        linkTo(methodOn(UsersApiController.class).getUsers()).withRel("all-users")
    );
  
    return ResponseEntity.ok(userModel);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<EntityModel<MessageResponse>> destroyPost(@PathVariable Long id) {
    userService.delete(id);

    MessageResponse message = new MessageResponse("User Successfully deleted");

    EntityModel<MessageResponse> response = EntityModel.of(message,
        linkTo(methodOn(UsersApiController.class).getUsers()).withRel("all-users")
    );

    return ResponseEntity.ok(response);
  }
}