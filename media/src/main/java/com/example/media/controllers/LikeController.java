package com.example.media.controllers;

import com.example.media.entities.Like;
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
    public List<Like> getAllLikes() {
        return likeService.getAllLikes();
    }

    @GetMapping("/post/{postId}")
    public List<Like> getPostLikes(@PathVariable Long postId) {
        return likeService.getPostLikes(postId);
    }

    @GetMapping("/user/{userId}")
    public List<Like> getUserLikes(@PathVariable Long userId) {
        return likeService.getUserLikes(userId);
    }

    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId) {
        return likeService.getLike(likeId);
    }

    @PostMapping()
    public Like createLike(@RequestBody Like like) {
        return likeService.createLike(like);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long Id) {
        likeService.deleteLike(Id);
    }
}
