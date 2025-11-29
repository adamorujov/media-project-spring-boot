package com.example.media.repos;

import com.example.media.entities.Comment;
import com.example.media.entities.Like;
import com.example.media.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(nativeQuery = true, value = "select * from comments where post_id = :id")
    List<Comment> findComments(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from likes where post_id = :id")
    List<Like> findLikes(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select * from posts where user_id = :id")
    List<Post> findUserPosts(@Param("id") Long id);
}
