package com.maniek.software.workoutnotepad.user;

import java.util.Optional;

public interface CustomUserRepository {

    Optional<User> findByUsername(String username);

}
