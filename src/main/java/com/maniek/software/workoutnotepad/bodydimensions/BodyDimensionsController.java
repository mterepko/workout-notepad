package com.maniek.software.workoutnotepad.bodydimensions;

import com.maniek.software.workoutnotepad.user.User;
import com.maniek.software.workoutnotepad.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BodyDimensionsController {

    private final BodyDimensionsService bodyDimensionsService;

    private final UserService userService;

    @GetMapping("/bodyDimensions")
    public String addBodyDimensions(Model model){


        return "addBodyDimensions";
    }

    @PostMapping("/bodyDimensions")
    public String addBodyDimensions(@Valid BodyDimensionsRequest bodyDimensionsRequest){

        User user = userService.findUserByUsername(bodyDimensionsRequest.getUserName());

        BodyDimensions bodyDimensions = new BodyDimensions(bodyDimensionsRequest.getGender(),
                bodyDimensionsRequest.getWeight(), bodyDimensionsRequest.getHeight(),
                bodyDimensionsRequest.getNeckSize(), bodyDimensionsRequest.getBicepsSize(),
                bodyDimensionsRequest.getChestSize(), bodyDimensionsRequest.getForearmSize(),
                bodyDimensionsRequest.getWaistSize(), bodyDimensionsRequest.getHipsSize(),
                bodyDimensionsRequest.getThighSize(), bodyDimensionsRequest.getCalfSize(), user);

        return "index";
    }
}
