package com.maniek.software.workoutnotepad.user;

import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensions;
import com.maniek.software.workoutnotepad.bodydimensions.BodyDimensionsRequest;

import com.maniek.software.workoutnotepad.exercise.Exercise;
import com.maniek.software.workoutnotepad.exercise.ExerciseRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with username %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
    }

    public void singUpUser(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        boolean usernameExists = userRepository.findByUsername(user.getUsername())
                .isPresent();

        boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(usernameExists){
            throw new UsernameAlreadyExistsException("email or username already taken");
        } else if(emailExists){
            throw new EmailAlreadyExistsException("User with provided email already exists");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

    }

    public User findUserByUsername(String username){

        return userRepository.findByUsername(username).orElse(null);

    }

    public void addBodyDimensions(String name, BodyDimensionsRequest bodyDimensionsRequest){

        User user = userRepository.findByUsername(name).orElse(null);

        if(user == null) return;

        user.addBodyDimensions(new BodyDimensions(bodyDimensionsRequest.getWeight(), bodyDimensionsRequest.getHeight(),
                bodyDimensionsRequest.getNeckSize(), bodyDimensionsRequest.getBicepsSize(),
                bodyDimensionsRequest.getChestSize(), bodyDimensionsRequest.getForearmSize(),
                bodyDimensionsRequest.getWaistSize(), bodyDimensionsRequest.getHipsSize(),
                bodyDimensionsRequest.getThighSize(), bodyDimensionsRequest.getCalfSize(), new Date()));

        userRepository.save(user);


    }

    public void addExercise(String name, ExerciseRequest exerciseRequest){

        User user = userRepository.findByUsername(name).orElse(null);

        if(user == null) return;

        user.addExercise(new Exercise(exerciseRequest.getName(), exerciseRequest.isHasReps(),
                exerciseRequest.isHasWeight(), exerciseRequest.isHasSeries(), exerciseRequest.isHasTime(),
                exerciseRequest.getDescription(), new Date()));

        userRepository.save(user);
    }







}
