package com.example.media.services;

import com.example.media.entities.Like;
import com.example.media.entities.Post;
import com.example.media.entities.User;
import com.example.media.repos.LikeRepository;
import com.example.media.repos.PostRepository;
import com.example.media.repos.UserRepository;
import com.example.media.requests.LikeCreateRequest;
import com.example.media.responses.LikeResponse;
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

    public List<LikeResponse> getAllLikes() {
        List<Like> likes = likeRepository.findAll();
        return likes.stream().map(LikeResponse::new).toList();
    }

    public List<LikeResponse> getPostLikes(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post does not exist"));
        List<Like> likes = likeRepository.findByPostId(postId);
        return likes.stream().map(LikeResponse::new).toList();
    }

    public List<LikeResponse> getUserLikes(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist"));
        List<Like> likes = likeRepository.findByUserId(userId);
        return likes.stream().map(LikeResponse::new).toList();
    }

    public LikeResponse getLike(Long Id) {
        Optional<Like> like = likeRepository.findById(Id);
        return like.map(LikeResponse::new).orElse(null);
    }

    public LikeResponse createLike(LikeCreateRequest likeCreateRequest) {
        Optional<User> user = userRepository.findById(likeCreateRequest.getUserId());
        Optional<Post> post = postRepository.findById(likeCreateRequest.getPostId());
        if (user.isEmpty() && post.isEmpty()) {
            throw new RuntimeException("Post and user does not exist.");
        } else if (user.isEmpty()) {
            throw new RuntimeException("User does not exist.");
        } else if (post.isEmpty()) {
            throw new RuntimeException("Post does not exist.");
        } else {
            Like like = new Like();
            like.setId(likeCreateRequest.getId());
            like.setUser(user.get());
            like.setPost(post.get());
            likeRepository.save(like);
            return new LikeResponse(like);
        }
    }

    public void deleteLike(Long Id) {
        likeRepository.deleteById(Id);
    }
}
