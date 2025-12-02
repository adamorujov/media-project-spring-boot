package com.example.media.services;

import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import com.example.media.requests.PostCreateRequest;
import com.example.media.requests.PostUpdateRequest;
import com.example.media.responses.PostResponse;
import org.springframework.stereotype.Service;

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
        return posts.stream().map(PostResponse::new).toList();
    }

    public List<PostResponse> getUserPosts(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            List<Post> posts = postRepository.findUserPosts(userId);
            return posts.stream().map(PostResponse::new).toList();
        } else {
            return null;
        }
    }

    public PostResponse getPost(Long Id) {
        Optional<Post> post = postRepository.findById(Id);
        return post.map(PostResponse::new).orElse(null);
    }

    public PostResponse createPost(PostCreateRequest postCreateRequest) {
        Optional<User> user = userRepository.findById(postCreateRequest.getUserId());
        if (user.isPresent()) {
            Post post = new Post();
            post.setId(postCreateRequest.getId());
            post.setUser(user.get());
            post.setTitle(postCreateRequest.getTitle());
            post.setText(postCreateRequest.getText());
            postRepository.save(post);
            return new PostResponse(post);
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
            return new PostResponse(foundPost);
        } else {
            return null;
        }
    }

    public void deletePost(Long Id) {
        postRepository.deleteById(Id);
    }
}
