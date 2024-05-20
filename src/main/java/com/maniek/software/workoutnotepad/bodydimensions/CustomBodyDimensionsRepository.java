package com.maniek.software.workoutnotepad.bodydimensions;

import java.util.Optional;

public interface CustomBodyDimensionsRepository {

    Optional<BodyDimensions> findUserLatestBodyDimensions(String username);


}
