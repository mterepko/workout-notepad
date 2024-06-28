package com.maniek.software.workoutnotepad.workoutResult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutResultRepository extends JpaRepository<WorkoutResult, Long>, CustomWorkoutResultRepository {

    Optional<WorkoutResult> findByIdAndUserUsername(Long id, String username);
}
