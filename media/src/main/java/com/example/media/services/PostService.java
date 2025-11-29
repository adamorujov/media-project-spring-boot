package com.example.media.services;

import com.example.media.entities.Post;
import com.example.media.repos.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPost(Long Id) {
        return postRepository.findById(Id).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long Id, Post newPost) {
        Optional<Post> post = postRepository.findById(Id);
        if (post.isPresent()) {
            Post foundPost = post.get();
            foundPost.setTitle(newPost.getText());
            foundPost.setText(newPost.getText());
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
