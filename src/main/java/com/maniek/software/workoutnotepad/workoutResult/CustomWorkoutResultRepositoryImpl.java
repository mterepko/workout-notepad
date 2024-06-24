package com.maniek.software.workoutnotepad.workoutResult;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CustomWorkoutResultRepositoryImpl implements CustomWorkoutResultRepository{

    private final EntityManager entityManager;

    @Override
    public List<WorkoutResult> findWorkoutResultsByUsername(String username) {

        TypedQuery<WorkoutResult> query = entityManager.createQuery(
                "SELECT w FROM WorkoutResult w JOIN FETCH w.listOfExerciseResults er"
                    + " JOIN FETCH er.exercise WHERE w.user.username = :data", WorkoutResult.class
        );
        query.setParameter("data", username);

        return query.getResultList();
    }

    @Override
    public Optional<WorkoutResult> findUsersWorkoutResultById(String username, Long id) {
        try{
            TypedQuery<WorkoutResult> query = entityManager.createQuery(
                    "SELECT wr from WorkoutResult wr WHERE wr.id = :id AND wr.user.username = :username",
                    WorkoutResult.class
            );
            query.setParameter("id", id);
            query.setParameter("username", username);

            WorkoutResult workoutResult = query.getSingleResult();

            return Optional.of(workoutResult);

        } catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<WorkoutResult> findUsersWorkoutResultWithExerciseResultsById(String username, Long id) {
        try{
            TypedQuery<WorkoutResult> query = entityManager.createQuery(
                    "SELECT wr from WorkoutResult wr JOIN FETCH wr.listOfExerciseResults er"
                        + " WHERE wr.id = :id AND wr.user.username = :username", WorkoutResult.class
            );
            query.setParameter("id", id);
            query.setParameter("username", username);

            WorkoutResult workoutResult = query.getSingleResult();

            return Optional.of(workoutResult);

        } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
