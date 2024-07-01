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
    private final ExerciseRepository exerciseRepository;
    private final ExerciseService exerciseService;
    private final WorkoutRepository workoutRepository;
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

//    public void addWorkout(String name, WorkoutRequest workoutRequest)
//            throws WorkoutAlreadyExistsException, WorkoutNameAlreadyExistsException {
//
//        User user = userRepository.findUserWithWorkoutsByUsername(name).orElse(null);
//
//        if(user == null) return;
//
//        List<Exercise> exerciseList = exerciseRepository.findAllByIdIn(workoutRequest.getExerciseIds());
//
//        boolean workoutExists = user.getListOfWorkouts().stream()
//                .anyMatch(existingWorkout -> existingWorkout.equals(
//                        new Workout(workoutRequest.getName(), new Date(), exerciseList)));
//
//        boolean workoutNameExists = user.getListOfWorkouts().stream()
//                .anyMatch(existingWorkout -> existingWorkout.getName().equals(workoutRequest.getName()));
//
//        if(workoutExists){
//            throw new WorkoutAlreadyExistsException("You already have a workout with those exercises");
//        } else if(workoutNameExists){
//            throw new WorkoutNameAlreadyExistsException("You already have the workout with this name");
//        }
//        user.addWorkout(new Workout(workoutRequest.getName(), new Date(), exerciseList));
//
//        userRepository.save(user);
//    }

    @Transactional
    public void addWorkout(String name, WorkoutRequest workoutRequest){

        User user = userRepository.findByUsername(name).orElseThrow(()-> new RuntimeException("There is no such user"));
        Workout workout = workoutService.createWorkout(user, workoutRequest);
        user.addWorkout(workout);
        userRepository.save(user);
    }
    public void addWorkoutResult(String name, WorkoutResultRequest workoutResultRequest)
            throws WorkoutResultExistsException, WorkoutResultNameExistsException {

        User user = userRepository.findUserWithWorkoutResultsByUsername(name).orElse(null);

        if(user == null) return;

        Workout workout = workoutRepository.findWorkoutById(workoutResultRequest.getWorkoutId()).orElse(null);

        WorkoutResult workoutResult = new WorkoutResult(workoutResultRequest.getName(),workout, workoutResultRequest.getWorkoutDate());

        for(ExerciseResultRequest exerciseResultRequest : workoutResultRequest.getExerciseResults()){

            Exercise exercise = exerciseRepository.findById(exerciseResultRequest.getExerciseId()).orElse(null);
            workoutResult.addExerciseResult(new ExerciseResult(
                    exerciseResultRequest.getRepsCount(),
                    exerciseResultRequest.getWeight(),
                    exerciseResultRequest.getSeriesCount(),
                    exerciseResultRequest.getTimeOfExerciseSeconds(),
                    exercise
            ));

        }

        boolean workoutResultNameExists = user.getListOfWorkoutResults().stream()
                .anyMatch(existingWorkoutResult -> existingWorkoutResult.getName().equals(workoutResultRequest.getName()));

        boolean workoutResultExists = user.getListOfWorkoutResults().stream()
                .anyMatch(existingWorkoutResult -> existingWorkoutResult.equals(workoutResult));

        if(workoutResultExists){
            throw new WorkoutResultExistsException("You already have exactly the same workout completed!");
        } else if(workoutResultNameExists){
            throw new WorkoutResultNameExistsException("You already have a completed workout with this name!");
        }
        user.addWorkoutResult(workoutResult);
        userRepository.save(user);
    }

    @Transactional
    public void updateWorkoutResult(String name, Long workoutResultId, WorkoutResultRequest workoutResultRequest)
            throws WorkoutResultNoExistsException {

        workoutResultService.updateWorkoutResult(name, workoutResultId, workoutResultRequest);
    }

}
