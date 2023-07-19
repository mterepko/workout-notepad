package com.maniek.software.workoutnotepad.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/home")
    //@ResponseBody
    public String currentUserName(Principal principal) {


        User tempUser = userService.findUserByUsername(principal.getName());
        return tempUser.toString();
    }


    @GetMapping("/test/footer")
    String footerTest(){

        return "header";
    }


    @GetMapping("/test/header")
    String headerTest(){

        return "asdsa";
    }



}
