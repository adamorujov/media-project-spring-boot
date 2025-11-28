package com.example.media.repos;

import com.example.media.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPostId(Long id);
    List<Like> findByUserId(Long id);
}
