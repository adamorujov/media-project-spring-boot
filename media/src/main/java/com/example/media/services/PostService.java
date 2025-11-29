package com.example.media.services;

import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import com.example.media.requests.PostCreateRequest;
import com.example.media.requests.PostUpdateRequest;
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

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getUserPosts(Long userId) {
        return postRepository.findUserPosts(userId);
    }

    public Post getPost(Long Id) {
        return postRepository.findById(Id).orElse(null);
    }

    public Post createPost(PostCreateRequest postCreateRequest) {
        Optional<User> user = userRepository.findById(postCreateRequest.getUserId());
        if (user.isPresent()) {
            Post post = new Post();
            post.setId(postCreateRequest.getId());
            post.setUser(user.get());
            post.setTitle(postCreateRequest.getTitle());
            post.setText(postCreateRequest.getText());
            postRepository.save(post);
            return post;
        } else {
            return null;
        }
    }

    public Post updatePost(Long Id, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(Id);
        if (post.isPresent()) {
            Post foundPost = post.get();
            foundPost.setTitle(postUpdateRequest.getText());
            foundPost.setText(postUpdateRequest.getText());
            postRepository.save(foundPost);
            return foundPost;
        } else {
            return null;
        }
    }

    public void deletePost(Long Id) {
        postRepository.deleteById(Id);
    }
}
