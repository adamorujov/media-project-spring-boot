package com.example.media.requests;

import lombok.Data;

@Data
public class PostCreateRequest {
    private Long id;
    private Long userId;
    private String title;
    private String text;
}
