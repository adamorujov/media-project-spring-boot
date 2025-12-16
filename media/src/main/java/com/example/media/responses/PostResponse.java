package com.example.media.responses;

import com.example.media.entities.Post;
import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private Long id;
    private Long userId;
    private String username;
    private String title;
    private String text;
    private List<LikeResponse> likes;

    public PostResponse(Post post, List<LikeResponse> likes) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.text = post.getText();
        this.likes = likes;
    }
}
