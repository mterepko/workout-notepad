package com.maniek.software.workoutnotepad.exercise;

import com.maniek.software.workoutnotepad.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    List<Exercise> findByUserId(Long UserId);
}
