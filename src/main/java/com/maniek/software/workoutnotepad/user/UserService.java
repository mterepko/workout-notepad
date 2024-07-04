package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.bodydimensions.*;

import com.maniek.software.workoutnotepad.exercise.*;
import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResult;
import com.maniek.software.workoutnotepad.exerciseResult.ExerciseResultRequest;
import com.maniek.software.workoutnotepad.workout.*;
import com.maniek.software.workoutnotepad.workoutResult.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";
    private final UserRepository userRepository;
    private final ExerciseService exerciseService;
    private final WorkoutService workoutService;
    private final WorkoutResultService workoutResultService;
    private final BodyDimensionsService bodyDimensionsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    @Transactional
    public void singUpUser(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        boolean usernameExists = userRepository.findUserByUsername(user.getUsername())
                .isPresent();

        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(usernameExists){
            throw new UsernameAlreadyExistsException("email or username already taken");
        } else if(emailExists){
            throw new EmailAlreadyExistsException("User with provided email already exists");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    public BodyDimensions getLatestBodyDimensions(String username){

        return bodyDimensionsService.findUsersLatestBodyDimensions(username);
    }

    @Transactional
    public void addBodyDimensions(String name, BodyDimensionsRequest bodyDimensionsRequest){

        User user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("There is no such user"));

        user.addBodyDimensions(bodyDimensionsService.saveBodyDimensions(bodyDimensionsRequest, name));

        userRepository.save(user);
    }

    public boolean hasBodyDimensions(String username){

        return bodyDimensionsService.hasUserBodyDimensions(username);
    }

    @Transactional
    public void updateUsersHeight(String username, BodyDimensionsHeightRequest bodyDimensionsHeightRequest) {

        bodyDimensionsService.updateHeight(username, bodyDimensionsHeightRequest.getHeight());
    }

    @Transactional
    public void addExercise(String name, ExerciseRequest exerciseRequest){

        User user = userRepository.findByUsername(name).orElseThrow(()-> new RuntimeException("There is no such user"));

        Exercise exercise = exerciseService.createExercise(exerciseRequest, user);

        user.addExercise(exercise);

        userRepository.save(user);
    }

    @Transactional
    public void addWorkout(String name, WorkoutRequest workoutRequest){

        User user = userRepository.findByUsername(name).orElseThrow(()-> new RuntimeException("There is no such user"));
        Workout workout = workoutService.createWorkout(user, workoutRequest);
        user.addWorkout(workout);
        userRepository.save(user);
    }

    @Transactional
    public void addWorkoutResult(String name, WorkoutResultRequest workoutResultRequest){

        User user = userRepository.findByUsername(name).orElseThrow(()-> new RuntimeException("There is no such user"));
        WorkoutResult workoutResult = workoutResultService.createWorkoutResult(user, workoutResultRequest);
        user.addWorkoutResult(workoutResult);
        userRepository.save(user);
    }
    
    @Transactional
    public void updateWorkoutResult(String name, Long workoutResultId, WorkoutResultRequest workoutResultRequest)
            throws WorkoutResultNoExistsException {

        workoutResultService.updateWorkoutResult(name, workoutResultId, workoutResultRequest);
    }

}
