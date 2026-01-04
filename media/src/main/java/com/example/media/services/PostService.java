package com.example.media.services;

import com.example.media.entities.Like;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import com.example.media.requests.PostCreateRequest;
import com.example.media.requests.PostUpdateRequest;
import com.example.media.responses.LikeResponse;
import com.example.media.responses.PostResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map((post) -> {
            List<Like> likes = postRepository.findLikes(post.getId());
            List<LikeResponse> likeResponses = likes.stream().map(LikeResponse::new).toList();
            return new PostResponse(post, likeResponses);
        }).toList();
    }

    public List<PostResponse> getUserPosts(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Post> posts = postRepository.findUserPosts(userId);
            return posts.stream().map((post) -> {
                List<Like> likes = postRepository.findLikes(post.getId());
                List<LikeResponse> likeResponses = likes.stream().map(LikeResponse::new).toList();
                return new PostResponse(post, likeResponses);
            }).toList();
        } else {
            return null;
        }
    }

    public PostResponse getPost(Long Id) {
        Optional<Post> post = postRepository.findById(Id);
        return post.map((p) -> {
            List<Like> likes = postRepository.findLikes(p.getId());
            List<LikeResponse> likeResponses = likes.stream().map(LikeResponse::new).toList();
            return new PostResponse(p, likeResponses);
        }).orElse(null);
    }

    public PostResponse createPost(PostCreateRequest postCreateRequest) {
        Optional<User> user = userRepository.findById(postCreateRequest.getUserId());
        if (user.isPresent()) {
            Post post = new Post();
            post.setId(postCreateRequest.getId());
            post.setUser(user.get());
            post.setTitle(postCreateRequest.getTitle());
            post.setText(postCreateRequest.getText());
            post.setCreatedAt(LocalDateTime.now());
            postRepository.save(post);
            List<Like> likes = postRepository.findLikes(post.getId());
            List<LikeResponse> likeResponses = likes.stream().map(LikeResponse::new).toList();
            return new PostResponse(post, likeResponses);
        } else {
            return null;
        }
    }

    public PostResponse updatePost(Long Id, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(Id);
        if (post.isPresent()) {
            Post foundPost = post.get();
            foundPost.setTitle(postUpdateRequest.getTitle());
            foundPost.setText(postUpdateRequest.getText());
            postRepository.save(foundPost);
            List<Like> likes = postRepository.findLikes(foundPost.getId());
            List<LikeResponse> likeResponses = likes.stream().map(LikeResponse::new).toList();
            return new PostResponse(foundPost, likeResponses);
        } else {
            return null;
        }
    }

    public void deletePost(Long Id) {
        postRepository.deleteById(Id);
    }
}
