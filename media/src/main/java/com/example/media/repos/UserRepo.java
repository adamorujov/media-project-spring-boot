package com.example.media.repos;

import com.example.media.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {
    private final EntityManager entityManager;

    public UserRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> findAllUsers() {
        Query query = entityManager.createNativeQuery(
                "select * from users", User.class);
        return query.getResultList();
    }
}
