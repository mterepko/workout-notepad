package com.maniek.software.workoutnotepad.bodydimensions;

import com.maniek.software.workoutnotepad.exercise.Exercise;

import java.util.List;
import java.util.Optional;

public interface CustomBodyDimensionsRepository {

    Optional<BodyDimensions> findUsersLatestBodyDimensions(String username);

    Double findLatestHeightByUserUsername(String username);

}
