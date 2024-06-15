package com.maniek.software.workoutnotepad.bodydimensions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BodyDimensionsService {

    private final BodyDimensionsRepository bodyDimensionsRepository;

    public BodyDimensions findUsersLatestBodyDimensions(String username){

       BodyDimensions bodyDimensions = bodyDimensionsRepository.findUsersLatestBodyDimensions(username).orElse(null);

       return bodyDimensions;
    }

    public List<BodyDimensions> findUsersBodyDimensions(String username) {

        return bodyDimensionsRepository.findUsersBodyDimensions(username);
    }

}
