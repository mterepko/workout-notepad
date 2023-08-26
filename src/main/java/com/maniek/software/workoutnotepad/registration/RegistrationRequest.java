package com.maniek.software.workoutnotepad.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class RegistrationRequest{

    @NotEmpty(message="Username cannot be empty")
    private  String username;

    @NotEmpty(message="First name cannot be empty")
    private  String firstName;

    @NotEmpty(message="Email cannot be empty")
    @Email
    private  String email;

    @NotEmpty(message = "Password cannot be empty")
    private String password;



}