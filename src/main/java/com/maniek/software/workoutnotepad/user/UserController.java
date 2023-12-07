package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensionsRequest;
import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.exercise.ExerciseAlreadyExistsException;
import com.maniek.software.workoutnotepad.exercise.ExerciseRequest;
import com.maniek.software.workoutnotepad.exercise.ExerciseService;
import com.maniek.software.workoutnotepad.workout.WorkoutRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final ExerciseService exerciseService;

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {


        User tempUser = userService.findUserByUsername(principal.getName());

        model.addAttribute("user", tempUser);
        model.addAttribute("bodyDimension", tempUser.getListOfBodyDimensions());
        model.addAttribute("exercises", tempUser.getListOfExercises());
        return "index";
    }

    @GetMapping("/add-measurements")
    public String addBodyDimensions(Model model){



        model.addAttribute("bodyDimensionsRequest", new BodyDimensionsRequest());

        return "addBodyDimensions";
    }

    @PostMapping("add-measurements")
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
            System.out.println(bindingResult);
            return "addExercise";
        }
        try {
            userService.addExercise(principal.getName(), exerciseRequest);

        } catch (ExerciseAlreadyExistsException e) {
            bindingResult.rejectValue("name", "exerciseRequest.name",
                    "Exactly the same exercise already exists");
            model.addAttribute("exerciseRequest", exerciseRequest);
            return "addExercise";
        }


        return "redirect:/";
    }

    @GetMapping("/add-workout")
    public String addWorkout(Model model, Principal principal){

        User tempUser = userService.findUserByUsername(principal.getName());

        model.addAttribute("exercises", tempUser.getListOfExercises());
        model.addAttribute("otherUsersExercises", exerciseService.findOtherUsersExercises(principal.getName()));
        model.addAttribute("workoutRequest", new WorkoutRequest());
        return "addWorkout";
    }

    @PostMapping("/add-workout")
    public String addWorkout(@Valid WorkoutRequest workoutRequest, Model model, Principal principal){


        userService.addWorkout(principal.getName(), workoutRequest);

        return "redirect:/";
    }




}
