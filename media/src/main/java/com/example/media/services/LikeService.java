package com.example.media.services;

import com.example.media.entities.Like;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.LikeRepository;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository,
                       PostRepository postRepository,
                       UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    public List<Like> getPostLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post does not exist"));
        return likeRepository.findByPostId(postId);
    }

    public List<Like> getUserLikes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        return likeRepository.findByUserId(userId);
    }

    public Like getLike(Long Id) {
        return likeRepository.findById(Id).orElse(null);
    }

    public Like createLike(Like like) {
        return likeRepository.save(like);
    }

    public void deleteLike(Long Id) {
        likeRepository.deleteById(Id);
    }
}
