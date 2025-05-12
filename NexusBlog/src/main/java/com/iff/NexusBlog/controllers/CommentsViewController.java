package com.iff.NexusBlog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.iff.NexusBlog.models.Comment;
import com.iff.NexusBlog.models.Post;
import com.iff.NexusBlog.service.CommentService;
import com.iff.NexusBlog.service.PostService;

@Controller
public class CommentsViewController {
    private CommentService commentService;
    private PostService postService;

    public CommentsViewController(CommentService commentService, PostService postService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @PostMapping("comments/post/{id}")
    public String createComment(@PathVariable Long id, @ModelAttribute Comment comment) {
        Post post = postService.getById(id);
        comment.setId(null);
        commentService.create(comment, post.getId(), 6L);
        return "redirect:/posts/" + id;
    }

    @GetMapping("comments/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        Comment comment = commentService.getById(id);
        Long postId = comment.getPost().getId();
        commentService.delete(id);
        return "redirect:/posts/" + postId;
    }
}
