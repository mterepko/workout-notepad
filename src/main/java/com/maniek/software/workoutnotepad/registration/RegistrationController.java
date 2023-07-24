package com.maniek.software.workoutnotepad.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/register")
    public String register( RegistrationRequest request){

         registrationService.register(request);

         return "fragments/footer";
    }



    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("registrationData", new RegistrationRequest());

        return "signUpForm";
    }

}