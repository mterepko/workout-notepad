package com.maniek.software.workoutnotepad.registration;

import com.maniek.software.workoutnotepad.user.EmailAlreadyExistsException;
import com.maniek.software.workoutnotepad.user.UsernameAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping("/register")
    public String register(@Valid RegistrationRequest request, BindingResult bindingResult, Model model,
                           RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            model.addAttribute("registrationRequest", request);
            System.out.println(bindingResult);
            return "signUpForm";
        }
        try {
            registrationService.register(request);
        } catch (UsernameAlreadyExistsException | EmailAlreadyExistsException e) {
            if(e instanceof UsernameAlreadyExistsException){
                bindingResult.rejectValue("username", "registrationRequest.username",
                        "An account already exists for this username");
            } else if(e instanceof EmailAlreadyExistsException){
                bindingResult.rejectValue("email", "registrationRequest.email",
                        "An account already exists for this email");
            }
            model.addAttribute("registrationRequest", request);
            return "signUpForm";
        }


        return "redirect:/register/success";
    }



    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("registrationRequest", new RegistrationRequest());

        return "signUpForm";
    }

    @GetMapping("/login")
    public String showLoginPage(){

        return "login";
    }

    @GetMapping("/register/success")
    public String registerSuccess(){

        return "successfulRegistration";
    }

}