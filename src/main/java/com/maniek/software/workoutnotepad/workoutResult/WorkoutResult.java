package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResult;
import com.maniek.software.workoutnotepad.user.User;
import com.maniek.software.workoutnotepad.workout.Workout;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WorkoutResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate workoutDate;

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

    public WorkoutResult(String name, Workout workout, LocalDate workoutDate){
        this.name = name;
        this.workout = workout;
        this.workoutDate = workoutDate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkoutResult workoutResult = (WorkoutResult) o;

        if (!Objects.equals(workoutDate, workoutResult.workoutDate)) return false;
        if (!Objects.equals(workout, workoutResult.workout)) return false;

        return areExerciseResultListsEqual(listOfExerciseResults, workoutResult.listOfExerciseResults);
        //return Objects.equals(listOfExerciseResults, workoutResult.listOfExerciseResults);
    }

    @Override
    public int hashCode() {
        int result = workoutDate != null ? workoutDate.hashCode() : 0;
        result = 31 * result + (workout != null ? workout.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (listOfExerciseResults != null ? listOfExerciseResults.hashCode() : 0);
        return result;
    }

    private <T> boolean areExerciseResultListsEqual(List<T> list1, List<T> list2) {
        Set<T> set1 = new HashSet<>(list1);
        Set<T> set2 = new HashSet<>(list2);

        return set1.equals(set2);
    }
}
