package com.example.media.controllers;

import com.example.media.entities.Like;
import com.example.media.requests.LikeCreateRequest;
import com.example.media.responses.LikeResponse;
import com.example.media.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/post/{postId}")
    public List<LikeResponse> getPostLikes(@PathVariable Long postId) {
        return likeService.getPostLikes(postId);
    }

    @GetMapping("/user/{userId}")
    public List<LikeResponse> getUserLikes(@PathVariable Long userId) {
        return likeService.getUserLikes(userId);
    }

    @GetMapping("/{likeId}")
    public LikeResponse getLike(@PathVariable Long likeId) {
        return likeService.getLike(likeId);
    }

    @PostMapping()
    public LikeResponse createLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        return likeService.createLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
    }
}
