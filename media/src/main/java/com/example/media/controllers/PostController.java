package com.example.media.controllers;

import com.example.media.entities.Post;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody Post newPost) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post foundPost = post.get();
            foundPost.setTitle(newPost.getTitle());
            foundPost.setText(newPost.getText());
            postRepository.save(foundPost);
            return foundPost;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);

    }

}
