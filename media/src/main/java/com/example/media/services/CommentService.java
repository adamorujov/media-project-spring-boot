package com.example.media.services;

import com.example.media.entities.Comment;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.CommentRepository;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> getPostComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post does not exist."));
        return commentRepository.findByPostId(postId);
    }

    public List<Comment> getUserComments(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist."));
        return commentRepository.findByUserId(userId);
    }

    public Comment getComment(Long Id) {
        return commentRepository.findById(Id).orElse(null);
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long Id, Comment newComment) {
        Optional<Comment> comment = commentRepository.findById(Id);
        if (comment.isPresent()) {
            Comment foundComment = comment.get();
            foundComment.setText(newComment.getText());
            commentRepository.save(foundComment);
            return foundComment;
        } else {
            return null;
        }
    }

    public void deleteComment(Long Id) {
        commentRepository.deleteById(Id);
    }
}
