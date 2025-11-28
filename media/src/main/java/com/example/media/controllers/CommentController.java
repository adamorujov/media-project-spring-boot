package com.example.media.controllers;

import com.example.media.entities.Comment;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.CommentRepository;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentController(CommentRepository commentRepository,
                             PostRepository postRepository,
                             UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    @GetMapping("/post/{postId}")
    public List<Comment> getPostComments(@PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post does not exist."));
        return commentRepository.findByPostId(postId);
    }

    @GetMapping("/user/{userId}")
    public List<Comment> getUserComments(@PathVariable Long Id) {
        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User does not exist."));
        return commentRepository.findByUserId(Id);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody Comment newComment) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            Comment foundComment = comment.get();
            foundComment.setText(newComment.getText());
            commentRepository.save(foundComment);
            return foundComment;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long Id) {
        commentRepository.deleteById(Id);
    }
}