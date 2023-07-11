package com.maniek.software.workoutnotepad.model;

import com.maniek.software.workoutnotepad.user.User;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Workout {


    @Id
    private Long id;

    private LocalDate workoutDate;

    @OneToMany
    private Set<ExerciseResults> exercises;

    @Enumerated(value = EnumType.STRING)
    private ProcessStatus processStatus;
    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public Set<ExerciseResults> getExercises() {
        return exercises;
    }

    public void setExercises(Set<ExerciseResults> exercises) {
        this.exercises = exercises;
    }

    public ProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(ProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
