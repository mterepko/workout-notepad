package com.maniek.software.workoutnotepad.exercise;

import com.maniek.software.workoutnotepad.user.User;
import com.maniek.software.workoutnotepad.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RestController
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final UserService userService;



}
