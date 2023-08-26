package com.maniek.software.workoutnotepad.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String homePage(Principal principal, Model model) {


        User tempUser = userService.findUserByUsername(principal.getName());
        System.out.println(principal);

        return "index";
    }


    @GetMapping("/test/footer")
    String footerTest(){

        return "fragments/header";
    }


    @GetMapping("/test/header")
    String headerTest(){

        return "asdsa";
    }



}
