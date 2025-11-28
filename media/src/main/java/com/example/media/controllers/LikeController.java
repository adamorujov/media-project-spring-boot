package com.example.media.controllers;

import com.example.media.entities.Like;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.LikeRepository;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeController(LikeRepository likeRepository,
                          PostRepository postRepository,
                          UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @PostMapping
    public Like createLike(@RequestBody Like like) {
        return likeRepository.save(like);
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long Id) {
        return likeRepository.findById(Id).orElse(null);
    }

    @GetMapping("/post/{postId}")
    public List<Like> getPostLikes(@PathVariable Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post does not exist."));
        return likeRepository.findByPostId(postId);
    }

    @GetMapping("/user/{userId}")
    public List<Like> getUserLikes(@PathVariable Long Id) {
        User user = userRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User does not exist."));
        return likeRepository.findByUserId(Id);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
