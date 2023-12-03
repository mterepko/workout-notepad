package com.maniek.software.workoutnotepad.workout;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Workout {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date creationDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name="workout_exercise",
            joinColumns = @JoinColumn(name="workout_id"),
            inverseJoinColumns = @JoinColumn(name="exercise_id")
    )
    private List<Exercise> exercises;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Workout(String name, Date creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }
}
