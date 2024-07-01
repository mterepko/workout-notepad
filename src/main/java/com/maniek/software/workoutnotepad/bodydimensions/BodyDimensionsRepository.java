package com.maniek.software.workoutnotepad.bodydimensions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BodyDimensionsRepository extends JpaRepository<BodyDimensions, Long>, CustomBodyDimensionsRepository{

    List<BodyDimensions> findAllByUserUsername(String username);

}
