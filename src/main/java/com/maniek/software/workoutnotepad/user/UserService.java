package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensions;
import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensionsRequest;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.exercise.ExerciseAlreadyExistsException;
import com.maniek.software.workoutnotepad.exercise.ExerciseRepository;
import com.maniek.software.workoutnotepad.exercise.ExerciseRequest;
import com.maniek.software.workoutnotepad.workout.Workout;
import com.maniek.software.workoutnotepad.workout.WorkoutAlreadyExistsException;
import com.maniek.software.workoutnotepad.workout.WorkoutNameAlreadyExistsException;
import com.maniek.software.workoutnotepad.workout.WorkoutRequest;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResultRequest;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

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

    public User findUserByUsername(String username){

        return userRepository.findUserByUsername(username).orElse(null);

    }

    public void addBodyDimensions(String name, BodyDimensionsRequest bodyDimensionsRequest){

        User user = userRepository.findByUsername(name).orElse(null);

        if(user == null) return;

        user.addBodyDimensions(new BodyDimensions(bodyDimensionsRequest.getWeight(), bodyDimensionsRequest.getHeight(),
                bodyDimensionsRequest.getNeckSize(), bodyDimensionsRequest.getBicepsSize(),
                bodyDimensionsRequest.getChestSize(), bodyDimensionsRequest.getForearmSize(),
                bodyDimensionsRequest.getWaistSize(), bodyDimensionsRequest.getHipsSize(),
                bodyDimensionsRequest.getThighSize(), bodyDimensionsRequest.getCalfSize(), new Date()));

        userRepository.save(user);


    }

    public void addExercise(String name, ExerciseRequest exerciseRequest) throws ExerciseAlreadyExistsException {

        User user = userRepository.findUserWithExercisesByUsername(name).orElse(null);

        if(user == null) return;

        boolean exerciseExists = user.getListOfExercises().stream()
                .anyMatch(existingExercise -> existingExercise.equals(new Exercise(
                        exerciseRequest.getName(),
                        exerciseRequest.isHasReps(),
                        exerciseRequest.isHasWeight(),
                        exerciseRequest.isHasSeries(),
                        exerciseRequest.isHasTime(),
                        exerciseRequest.getDescription(),
                        new Date())));

        if(exerciseExists) {
            throw new ExerciseAlreadyExistsException("This user already have the same exercise");
        }



        user.addExercise(new Exercise(exerciseRequest.getName(), exerciseRequest.isHasReps(),
                exerciseRequest.isHasWeight(), exerciseRequest.isHasSeries(), exerciseRequest.isHasTime(),
                exerciseRequest.getDescription(), new Date()));

        userRepository.save(user);
    }

    public void addWorkout(String name, WorkoutRequest workoutRequest)
            throws WorkoutAlreadyExistsException, WorkoutNameAlreadyExistsException {

        User user = userRepository.findUserWithWorkoutsByUsername(name).orElse(null);

        if(user == null) return;

        List<Exercise> exerciseList = exerciseRepository.findAllByIdIn(workoutRequest.getExerciseIds());

        boolean workoutExists = user.getListOfWorkouts().stream()
                .anyMatch(existingWorkout -> existingWorkout.equals(
                        new Workout(workoutRequest.getName(), new Date(), exerciseList)));


        boolean workoutNameExists = user.getListOfWorkouts().stream()
                .anyMatch(existingWorkout -> existingWorkout.getName().equals(workoutRequest.getName()));

        if(workoutExists){
            throw new WorkoutAlreadyExistsException("You already have a workout with those exercises");
        } else if(workoutNameExists){
            throw new WorkoutNameAlreadyExistsException("You already have the workout with this name");
        }
        user.addWorkout(new Workout(workoutRequest.getName(), new Date(), exerciseList));

        userRepository.save(user);
    }

    public void addWorkoutResult(String name, WorkoutResultRequest workoutResultRequest){
        User user = userRepository.findUserWithWorkoutsByUsername(name).orElse(null);


        if(user == null) return;
    }









}
