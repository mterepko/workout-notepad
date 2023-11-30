package com.maniek.software.workoutnotepad.model;

import com.maniek.software.workoutnotepad.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Workout {


    @Id
    private Long id;

    private Date workoutDate;

//    @OneToMany
//    private Set<ExerciseResults> exercises;

    @Enumerated(value = EnumType.STRING)
    private ProcessStatus processStatus;
    @ManyToOne
    private User user;


}
