package com.maniek.software.workoutnotepad.workoutResult;

import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResultRequest;
import com.maniek.software.workoutnotepad.validation.FutureDate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkoutResultRequest {

    @NotEmpty(message="Name of the completed workout is required")
    private String name;

    @NotNull
    private  Long workoutId;

    @FutureDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workoutDate;

    @Valid
    private List<ExerciseResultRequest> exerciseResults;
}
