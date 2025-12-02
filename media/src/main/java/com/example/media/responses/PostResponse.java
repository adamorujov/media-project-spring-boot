package com.example.media.responses;

import com.example.media.entities.Post;
import lombok.Data;

@Data
public class PostResponse {
    private Long id;
    private Long userId;
    private String username;
    private String title;
    private String text;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.userId = post.getUser().getId();
        this.username = post.getUser().getUsername();
        this.title = post.getTitle();
        this.text = post.getText();
    }
}
