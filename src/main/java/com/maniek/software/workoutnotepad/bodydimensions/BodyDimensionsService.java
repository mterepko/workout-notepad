package com.maniek.software.workoutnotepad.bodydimensions;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BodyDimensionsService {

    private final BodyDimensionsRepository bodyDimensionsRepository;


    public void create(BodyDimensions bodyDimensions){
        bodyDimensionsRepository.save(bodyDimensions);
    }

}
