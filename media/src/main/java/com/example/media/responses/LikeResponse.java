package com.example.media.responses;

import com.example.media.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long postId;

    public LikeResponse(Like like) {
        this.id = like.getId();
        this.userId = like.getUser().getId();
        this.username = like.getUser().getUsername();
        this.postId = like.getPost().getId();
    }
}
