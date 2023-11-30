package com.maniek.software.workoutnotepad.exercise;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CustomExerciseRepositoryImpl implements CustomExerciseRepository {

    private final EntityManager entityManager;

    @Override
    public List<Exercise> findUsersExercises(String username) {
        try {
            List<Exercise> exercises = entityManager.createQuery(
                            "SELECT e FROM Exercise e JOIN e.user u WHERE u.username = :username "
                                + "ORDER BY e.creationDate DESC", Exercise.class
                    )
                    .setParameter("username", username)
                    .getResultList();

            return exercises;
        } catch (NoResultException e) {
            return List.of();
        }
    }

    @Override
    public List<Exercise> findOtherUsersExercises(String username) {
        try {

            List<Exercise> exercises = entityManager.createQuery(
                            "SELECT e FROM Exercise e JOIN e.user u WHERE u.username <> :username "
                                + "ORDER BY e.creationDate DESC", Exercise.class
                    )
                    .setParameter("username", username)
                    .getResultList();

            return exercises;
        } catch (NoResultException e) {
            return List.of();
        }
    }
}
