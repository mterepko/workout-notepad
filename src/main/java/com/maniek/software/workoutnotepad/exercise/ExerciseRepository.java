package com.maniek.software.workoutnotepad.exercise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>, CustomExerciseRepository {

    List<Exercise> findOtherUsersExercises(String username);

    List<Exercise> findAllByIdIn(List<Long> exerciseIds);

    List<Exercise> findUsersExercises(String username);

    Optional<Exercise> findById(Long id);
}
