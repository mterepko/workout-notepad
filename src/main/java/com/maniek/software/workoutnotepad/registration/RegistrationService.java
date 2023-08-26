package com.maniek.software.workoutnotepad.registration;

import com.maniek.software.workoutnotepad.user.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public void register(RegistrationRequest request) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {

        userService.singUpUser(
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
