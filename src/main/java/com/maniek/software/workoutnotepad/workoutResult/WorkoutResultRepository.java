package com.maniek.software.workoutnotepad.workoutResult;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutResultRepository extends JpaRepository<WorkoutResult, Long>, CustomWorkoutResultRepository {

}
