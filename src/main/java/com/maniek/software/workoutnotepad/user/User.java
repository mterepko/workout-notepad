package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensions;
import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.workout.Workout;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResult;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private boolean isPrivate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exercise> listOfExercises;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BodyDimensions> listOfBodyDimensions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Workout> listOfWorkouts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkoutResult> listOfWorkoutResults;

    public User(String username, String name, String email, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.isPrivate = true;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());

        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    // support class to associate body dimension with user
    public void addBodyDimensions(BodyDimensions bodyDimensions) {
        if (listOfBodyDimensions == null) {
            listOfBodyDimensions = new ArrayList<>();
        }
        listOfBodyDimensions.add(bodyDimensions);

        bodyDimensions.setUser(this);
    }

    public void addExercise(Exercise exercise) {
        if (listOfExercises == null) {
            listOfExercises = new ArrayList<>();
        }
        listOfExercises.add(exercise);

        exercise.setUser(this);
    }

    public void addWorkout(Workout workout) {
        if (listOfWorkouts == null) {
            listOfWorkouts = new ArrayList<>();
        }
        listOfWorkouts.add(workout);

        workout.setUser(this);
    }

    public void addWorkoutResult(WorkoutResult workoutResult){
        if(listOfWorkoutResults == null){
            listOfWorkoutResults = new ArrayList<>();

        }
        listOfWorkoutResults.add(workoutResult);

        workoutResult.setUser(this);
    }
}
