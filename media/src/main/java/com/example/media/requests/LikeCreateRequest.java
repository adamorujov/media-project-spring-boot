package com.example.media.requests;

import lombok.Data;

@Data
public class LikeCreateRequest {
    private Long Id;
    private Long userId;
    private Long postId;
}
