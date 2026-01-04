package com.example.media.services;

import com.example.media.entities.Comment;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.CommentRepository;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import com.example.media.requests.CommentCreateRequest;
import com.example.media.responses.CommentResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(CommentResponse::new).toList();
    }

    public List<CommentResponse> getPostComments(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post does not exist."));
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map(CommentResponse::new).toList();
    }

    public List<CommentResponse> getUserComments(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist."));
        List<Comment> comments = commentRepository.findByUserId(userId);
        return comments.stream().map(CommentResponse::new).toList();
    }

    public CommentResponse getComment(Long Id) {
        Optional<Comment> comment = commentRepository.findById(Id);
        return comment.map(CommentResponse::new).orElse(null);
    }

    public CommentResponse createComment(CommentCreateRequest commentCreateRequest) {
        Optional<Post> post = postRepository.findById(commentCreateRequest.getPostId());
        Optional<User> user = userRepository.findById(commentCreateRequest.getUserId());
        System.out.println(post);
        System.out.println(user);
        if (post.isEmpty() && user.isEmpty()) {
            throw new RuntimeException("Post and user does not exist.");
        } else if (post.isEmpty()) {
            throw new RuntimeException("Post does not exist.");
        } else if (user.isEmpty()) {
            throw new RuntimeException("User does not exist.");
        } else {
            Comment comment = new Comment();
            System.out.println(commentCreateRequest.getId());
            System.out.println(commentCreateRequest.getId());
            comment.setId(commentCreateRequest.getId());
            comment.setPost(post.get());
            comment.setUser(user.get());
            comment.setText(commentCreateRequest.getText());
            comment.setCreatedAt(LocalDateTime.now());
            commentRepository.save(comment);
            return new CommentResponse(comment);
        }
    }

    public CommentResponse updateComment(Long Id, CommentCreateRequest commentCreateRequest) {
        Optional<Comment> comment = commentRepository.findById(Id);
        if (comment.isPresent()) {
            Comment foundComment = comment.get();
            foundComment.setText(commentCreateRequest.getText());
            commentRepository.save(foundComment);
            return new CommentResponse(foundComment);
        } else {
            return null;
        }
    }

    public void deleteComment(Long Id) {
        commentRepository.deleteById(Id);
    }
}
