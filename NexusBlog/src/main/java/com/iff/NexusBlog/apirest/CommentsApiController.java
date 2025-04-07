package com.iff.NexusBlog.apirest;

import org.springframework.web.bind.annotation.RestController;

import com.iff.NexusBlog.dto.CommentDTO;
import com.iff.NexusBlog.models.Comment;
import com.iff.NexusBlog.response.MessageResponse;
import com.iff.NexusBlog.service.CommentService;
import com.iff.NexusBlog.service.UserService;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/comments")
public class CommentsApiController {
  
  @Autowired
  private CommentService commentService;


  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<CollectionModel<EntityModel<CommentDTO>>> getComments() {
    List<Comment> comments = commentService.getAll();

    List<EntityModel<CommentDTO>> commentModels = comments.stream()
        .map(comment -> {
          CommentDTO dto = new CommentDTO(comment);
          return EntityModel.of(dto,
              linkTo(methodOn(CommentsApiController.class).getCommentByAuthor(Long.valueOf(comment.getUser().getId().toString()))).withRel("author-comments"),
              linkTo(methodOn(CommentsApiController.class).getComments()).withSelfRel()
          );
        })
        .toList();
  
    CollectionModel<EntityModel<CommentDTO>> collectionModel = CollectionModel.of(commentModels);
    collectionModel.add(linkTo(methodOn(CommentsApiController.class).getComments()).withSelfRel());
  
    return ResponseEntity.ok(collectionModel);
  }
  
  @GetMapping("/user/{id}")
  public ResponseEntity<CollectionModel<EntityModel<CommentDTO>>> getCommentByAuthor(@PathVariable Long id) {
    List<Comment> comments = commentService.getCommentsByUser(id);

    List<EntityModel<CommentDTO>> commentModels = comments.stream()
    .map(comment -> {
      CommentDTO dto = new CommentDTO(comment);
      return EntityModel.of(dto,
          linkTo(methodOn(CommentsApiController.class).getComments()).withRel("all-comments"),
          linkTo(methodOn(CommentsApiController.class).getCommentByAuthor(id)).withSelfRel()
      );
    })
    .toList();

    CollectionModel<EntityModel<CommentDTO>> collectionModel = CollectionModel.of(commentModels);
    collectionModel.add(linkTo(methodOn(CommentsApiController.class).getCommentByAuthor(id)).withSelfRel());

    return ResponseEntity.ok(collectionModel);
  }

  @GetMapping("/post/{id}")
  public ResponseEntity<CollectionModel<EntityModel<CommentDTO>>> getCommentsByPost(@PathVariable Long id) {
    List<Comment> comments = commentService.getCommentsByPost(id);

    List<EntityModel<CommentDTO>> commentModels = comments.stream()
        .map(comment -> {
            CommentDTO dto = new CommentDTO(comment);
            return EntityModel.of(dto,
                linkTo(methodOn(CommentsApiController.class).getCommentByAuthor(comment.getUser().getId())).withRel("author-comments"),
                linkTo(methodOn(CommentsApiController.class).getCommentsByPost(id)).withSelfRel()
            );
        })
        .toList();

    CollectionModel<EntityModel<CommentDTO>> collectionModel = CollectionModel.of(commentModels);
    collectionModel.add(linkTo(methodOn(CommentsApiController.class).getCommentsByPost(id)).withSelfRel());

    return ResponseEntity.ok(collectionModel);
  }
  

  @PostMapping()
  public ResponseEntity<EntityModel<CommentDTO>> createComment(@RequestBody CommentDTO commentData) {
    Comment newComment = new Comment();
    newComment.setContent(commentData.getContent());

    Comment createdComment = commentService.create(newComment, commentData.getPost_id(), commentData.getUser_id());

    CommentDTO dto = new CommentDTO(createdComment);
  
    EntityModel<CommentDTO> commentModel = EntityModel.of(dto,
        linkTo(methodOn(CommentsApiController.class).getComments()).withRel("all-comments"),
        linkTo(methodOn(CommentsApiController.class).getCommentByAuthor(createdComment.getUser().getId())).withRel("author-comments"),
        linkTo(methodOn(CommentsApiController.class).createComment(commentData)).withSelfRel()
    );
  
    return ResponseEntity
        .created(linkTo(methodOn(CommentsApiController.class).createComment(commentData)).toUri())
        .body(commentModel);
  }
  

  @DeleteMapping("/{id}")
  public ResponseEntity<EntityModel<MessageResponse>> destroyComment(@PathVariable Long id){
    commentService.delete(id);

    MessageResponse message = new MessageResponse("Comment Successfully deleted");


    EntityModel<MessageResponse> response = EntityModel.of(message,
        linkTo(methodOn(CommentsApiController.class).getComments()).withRel("all-comments")
    );

    return ResponseEntity.ok(response);
  }
  
}
