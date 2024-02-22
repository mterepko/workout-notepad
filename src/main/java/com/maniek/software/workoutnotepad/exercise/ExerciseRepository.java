package com.maniek.software.workoutnotepad.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>, CustomExerciseRepository {

    List<Exercise> findOtherUsersExercises(String username);

    List<Exercise> findAllByIdIn(List<Long> exerciseIds);

    List<Exercise> findUsersExercises(String username);
}
