package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResultRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class WorkoutResultRequest {


    private String name;

    private  Long workoutId;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date workoutDate;

    private List<ExerciseResultRequest> exerciseResults;
}
