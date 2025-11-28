package com.example.media.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    private Long id;
    private Long userId;
    private String title;
    @Lob
    @Column(columnDefinition = "text")
    private String text;
}
