package com.example.media.requests;

import lombok.Data;

@Data
public class CommentCreateRequest {
    private Long Id;
    private Long userId;
    private Long postId;
    private String text;
}
