package com.example.media.services;

import com.example.media.entities.Comment;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.CommentRepository;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import com.example.media.requests.CommentCreateRequest;
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

    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        Optional<Post> post = postRepository.findById(commentCreateRequest.getPostId());
        Optional<User> user = userRepository.findById(commentCreateRequest.getUserId());
        if (post.isEmpty() && user.isEmpty()) {
            throw new RuntimeException("Post and user does not exist.");
        } else if (post.isEmpty()) {
            throw new RuntimeException("Post does not exist.");
        } else if (user.isEmpty()) {
            throw new RuntimeException("User does not exist.");
        } else {
            Comment comment = new Comment();
            comment.setId(commentCreateRequest.getId());
            comment.setPost(post.get());
            comment.setUser(user.get());
            comment.setText(commentCreateRequest.getText());
            return commentRepository.save(comment);
        }
    }

    public Comment updateComment(Long Id, CommentCreateRequest commentCreateRequest) {
        Optional<Comment> comment = commentRepository.findById(Id);
        if (comment.isPresent()) {
            Comment foundComment = comment.get();
            foundComment.setText(commentCreateRequest.getText());
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
