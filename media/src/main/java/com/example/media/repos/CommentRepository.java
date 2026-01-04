package com.example.media.repos;

import com.example.media.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long id);
    List<Comment> findByUserId(Long id);

    @Query(nativeQuery = true,
            value = "select 'commented on', c.post_id, u.avatar, u.username from " +
                    "comments c left join users u on u.id = c.user_id " +
                    "where c.post_id in :postIds limit 5")
    List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);
}