package com.example.media.controllers;

import com.example.media.entities.Post;
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

    @GetMapping("/{postId}")
    public Post getPost(Long postId) {
        return postService.getPost(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post post) {
        return postService.updatePost(postId, post);
    }

    @DeleteMapping("/postId")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

}
