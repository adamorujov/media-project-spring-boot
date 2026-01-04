package com.example.media.repos;

import com.example.media.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPostId(Long id);
    List<Like> findByUserId(Long id);

    @Query(nativeQuery = true,
            value = "select 'liked by', l.post_id, u.avatar, u.username from " +
                    "likes l left join users u on u.id = l.user_id " +
                    "where c.post_id in :postIds limit 5")
    List<Object> findUserLikesByPostId(@Param("postIds") List<Long> postIds);
}
