package com.maniek.software.workoutnotepad.workout;

import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Long>, CustomWorkoutRepository {
}
