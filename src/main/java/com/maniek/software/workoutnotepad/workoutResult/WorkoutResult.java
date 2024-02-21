package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResult;
import com.maniek.software.workoutnotepad.user.User;
import com.maniek.software.workoutnotepad.workout.Workout;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkoutResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date creationDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workoutResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ExerciseResult> listOfExerciseResults;

    public WorkoutResult(String name, Workout workout, Date creationDate){
        this.name = name;
        this.workout = workout;
        this.creationDate = creationDate;
    }
    public WorkoutResult(String name, List<ExerciseResult> listOfExerciseResults) {
        this.name = name;
        this.listOfExerciseResults = listOfExerciseResults;
    }

    public void addExerciseResult(ExerciseResult exerciseResult){
        if(listOfExerciseResults == null){
            listOfExerciseResults = new ArrayList<>();
        }
        listOfExerciseResults.add(exerciseResult);

        exerciseResult.setWorkoutResult(this);
    }

}
