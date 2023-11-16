package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final EntityManager entityManager;


    @Override
    public Optional<User> findByUsername(String username) {

        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u LEFT JOIN FETCH u.listOfBodyDimensions WHERE u.username = :data",
                    User.class
            );
            query.setParameter("data", username);
            User user = query.getSingleResult();

            // Fetch exercises separately
            List<Exercise> exercises = entityManager.createQuery(
                            "SELECT e FROM Exercise e WHERE e.user = :user "
                            + "ORDER BY e.creationDate DESC", Exercise.class
                    )
                    .setParameter("user", user)
                    .getResultList();

            user.setListOfExercises(exercises);

            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
