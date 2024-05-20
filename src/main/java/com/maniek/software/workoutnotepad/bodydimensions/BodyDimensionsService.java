package com.maniek.software.workoutnotepad.bodydimensions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BodyDimensionsService {

    private final BodyDimensionsRepository bodyDimensionsRepository;

    public BodyDimensions findUsersLatestBodyDimensions(String username){

       BodyDimensions bodyDimensions = bodyDimensionsRepository.findUserLatestBodyDimensions(username).orElse(null);

       return bodyDimensions;
    }
}
