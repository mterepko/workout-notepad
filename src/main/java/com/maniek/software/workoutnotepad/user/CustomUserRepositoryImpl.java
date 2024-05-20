package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.workout.Workout;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResult;
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
    public Optional<User> findUserByUsername(String username) {

        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u WHERE u.username = :data",
                    User.class
            );
            query.setParameter("data", username);
            User user = query.getSingleResult();

            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserWithWorkoutsByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u LEFT JOIN FETCH u.listOfWorkouts WHERE u.username = :data",
                    User.class
            );
            query.setParameter("data", username);
            User user = query.getSingleResult();

            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findUserWithWorkoutResultsByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u LEFT JOIN FETCH u.listOfWorkoutResults WHERE u.username = :data",
                    User.class
            );
            query.setParameter("data", username);
            User user = query.getSingleResult();

            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


    @Override
    public Optional<User> findUserWithExercisesByUsername(String username) {
        try {
            TypedQuery<User> query = entityManager.createQuery(
                    "SELECT u FROM User u LEFT JOIN FETCH u.listOfExercises WHERE u.username = :data",
                    User.class
            );
            query.setParameter("data", username);
            User user = query.getSingleResult();

            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


}