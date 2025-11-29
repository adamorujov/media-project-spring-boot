package com.example.media.controllers;

import com.example.media.entities.Comment;
import com.example.media.requests.CommentCreateRequest;
import com.example.media.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getPostComments(@PathVariable Long postId) {
        return commentService.getPostComments(postId);
    }

    @GetMapping("/user/{userId}")
    public List<Comment> getUserComments(@PathVariable Long userId) {
        return commentService.getUserComments(userId);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.createComment(commentCreateRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId,
                                 @RequestBody CommentCreateRequest commentCreateRequest) {
        return commentService.updateComment(commentId, commentCreateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}