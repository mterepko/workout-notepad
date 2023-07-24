package com.maniek.software.workoutnotepad.registration;

import com.maniek.software.workoutnotepad.user.UserRole;
import com.maniek.software.workoutnotepad.user.User;
import com.maniek.software.workoutnotepad.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return userService.singUpUser(
                new User(
                        request.getUsername(),
                        request.getFirstName(),
                        request.getEmail(),
                        request.getPassword(),
                        UserRole.USER
                )
        );
    }
}
