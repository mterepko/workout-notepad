package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensionsRequest;
import com.maniek.software.workoutnotepad.exercise.ExerciseAlreadyExistsException;
import com.maniek.software.workoutnotepad.exercise.ExerciseRequest;
import com.maniek.software.workoutnotepad.exercise.ExerciseService;
import com.maniek.software.workoutnotepad.workout.*;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResultRepository;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResultRequest;
import com.maniek.software.workoutnotepad.workoutResult.WorkoutResultService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final ExerciseService exerciseService;
    
    private final WorkoutService workoutService;

    private final WorkoutResultService workoutResultService;

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {

        User tempUser = userService.findUserByUsername(principal.getName());

        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        model.addAttribute("bodyDimension", tempUser.getListOfBodyDimensions());
        model.addAttribute("exercises", exerciseService.findUsersExercises(principal.getName()));
        model.addAttribute("workouts", workoutService.findWorkoutsByUsername(principal.getName()));
        return "index";
    }

    @GetMapping("/add-measurements")
    public String addBodyDimensions(Model model){

        model.addAttribute("bodyDimensionsRequest", new BodyDimensionsRequest());

        return "addBodyDimensions";
    }

    @PostMapping("/add-measurements")
    public String addBodyDimensions(@Valid BodyDimensionsRequest bodyDimensionsRequest, Model model,
                                    BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()){
            model.addAttribute("bodyDimensionsRequest", bodyDimensionsRequest);
            return "addBodyDimensions";
        }

        userService.addBodyDimensions(principal.getName(), bodyDimensionsRequest);

        return "redirect:/";
    }

    @GetMapping("/add-exercise")
    public String addExercise(Model model){

        model.addAttribute("exerciseRequest", new ExerciseRequest());

        return "addExercise";
    }

    @PostMapping("/add-exercise")
    public String addExercise(@Valid ExerciseRequest exerciseRequest, BindingResult bindingResult,Model model,
                              Principal principal){

        if(bindingResult.hasErrors()){
            model.addAttribute("exerciseRequest", exerciseRequest);
            return "addExercise";
        }
        try {
            userService.addExercise(principal.getName(), exerciseRequest);

        } catch (ExerciseAlreadyExistsException e) {
            bindingResult.rejectValue("name", "exerciseRequest.name",
                    e.getMessage());
            model.addAttribute("exerciseRequest", exerciseRequest);
            return "addExercise";
        }

        return "redirect:/";
    }

    @GetMapping("/add-workout")
    public String addWorkout(Model model, Principal principal){

        model.addAttribute("exercises", exerciseService.findUsersExercises(principal.getName()));
        model.addAttribute("otherUsersExercises", exerciseService.findOtherUsersExercises(principal.getName()));
        model.addAttribute("workoutRequest", new WorkoutRequest());
        return "addWorkout";
    }

    @PostMapping("/add-workout")
    public String addWorkout(@Valid WorkoutRequest workoutRequest,BindingResult bindingResult, Model model,
                             Principal principal){

        if(bindingResult.hasErrors()){
            model.addAttribute("exercises", exerciseService.findUsersExercises(principal.getName()));
            model.addAttribute("otherUsersExercises", exerciseService.findOtherUsersExercises(principal.getName()));
            model.addAttribute("workoutRequest", workoutRequest);
            System.out.println(bindingResult);
            return "addWorkout";
        }

        try {
            userService.addWorkout(principal.getName(), workoutRequest);
        } catch (WorkoutAlreadyExistsException | WorkoutNameAlreadyExistsException e){
            if(e instanceof WorkoutAlreadyExistsException){
                bindingResult.rejectValue("name", "workoutRequest.name",
                        ((WorkoutAlreadyExistsException) e).getMessage());
            } else {
                bindingResult.rejectValue("name", "workoutRequest.name",
                        ((WorkoutNameAlreadyExistsException) e).getMessage());
            }
            model.addAttribute("exercises", exerciseService.findUsersExercises(principal.getName()));
            model.addAttribute("otherUsersExercises", exerciseService.findOtherUsersExercises(principal.getName()));
            model.addAttribute("workoutRequest", workoutRequest);
            return "addWorkout";

        }

        return "redirect:/";
    }

    @GetMapping("/list-workouts")
    public String listWorkouts(Model model, Principal principal){

        model.addAttribute("workouts", workoutService.findWorkoutsByUsername(principal.getName()));
        model.addAttribute("otherUsersWorkouts", workoutService.findWorkoutsOfOtherUsers(principal.getName()));

        return "listWorkouts";
    }

    @GetMapping("/add-workoutResult")
    public String addWorkoutResult(@RequestParam(name = "workoutId", required = false) Long workoutId, Model model){

        model.addAttribute("workout", workoutService.findWorkoutById(workoutId));
        model.addAttribute("workoutResultRequest",new WorkoutResultRequest());

        return "addWorkoutResult";
    }


    @PostMapping("/add-workoutResult")
    public String addWorkoutResult(@Valid WorkoutResultRequest workoutResultRequest, BindingResult bindingResult,
                                   Principal principal){

        userService.addWorkoutResult(principal.getName(), workoutResultRequest);


        return "redirect:/";
    }

    @DeleteMapping("/delete-workoutResult/{id}")
    public String deleteWorkoutResult(@PathVariable("id") Long id, Principal principal){


        return "listWorkoutResults";
    }


    @GetMapping("/list-workout-results")
    public String listWorkoutResults(Model model, Principal principal){

        model.addAttribute("workoutResults", workoutResultService.workoutResultsByUsername(principal.getName()));

        return "listWorkoutResults";
    }


}
