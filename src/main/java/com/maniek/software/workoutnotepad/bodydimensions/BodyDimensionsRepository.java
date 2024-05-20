package com.maniek.software.workoutnotepad.bodydimensions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyDimensionsRepository extends JpaRepository<BodyDimensions, Long>, CustomBodyDimensionsRepository{
}
