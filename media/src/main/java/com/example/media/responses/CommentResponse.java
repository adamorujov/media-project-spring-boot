package com.example.media.responses;

import com.example.media.entities.Comment;
import lombok.Data;

@Data
public class CommentResponse {
    private Long id;
    private Long userId;
    private String username;
    private Long postId;
    private String text;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.userId = comment.getUser().getId();
        this.username = comment.getUser().getUsername();
        this.postId = comment.getPost().getId();
        this.text = comment.getText();
    }
}
