package com.maniek.software.workoutnotepad.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final EntityManager entityManager;


    @Override
    public Optional<User> findByUsername(String username) {

        TypedQuery<User> query = entityManager.createQuery("select u from User u "
                + "LEFT JOIN FETCH u.listOfBodyDimensions "
                + "WHERE u.username = :data", User.class);

        query.setParameter("data", username);

        User tempUser = null;
        try {

            tempUser = query.getSingleResult();

        } catch (NoResultException e) {

        }
        Optional<User> optUser = Optional.ofNullable(tempUser);

        return  optUser;
    }
}
