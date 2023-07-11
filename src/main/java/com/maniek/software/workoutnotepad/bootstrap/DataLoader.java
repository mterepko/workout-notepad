package com.maniek.software.workoutnotepad.bootstrap;

import com.maniek.software.workoutnotepad.user.UserRepository;
import com.maniek.software.workoutnotepad.user.User;
import org.springframework.boot.CommandLineRunner;

public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

    //#TODO logowanie, wyswietlanie uzytkownika, dodawanie cwiczen, tworzenie treningu, dodawanie cwiczen do treningu


    }
}
