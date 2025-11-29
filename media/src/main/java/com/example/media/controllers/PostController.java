package com.example.media.controllers;

import com.example.media.entities.Post;
import com.example.media.requests.PostCreateRequest;
import com.example.media.requests.PostUpdateRequest;
import com.example.media.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getALlPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/user/{userId}")
    public List<Post> getUserPosts(@PathVariable Long userId) {
        return postService.getUserPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest postCreateRequest) {
        return postService.createPost(postCreateRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        return postService.updatePost(postId, postUpdateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

}
