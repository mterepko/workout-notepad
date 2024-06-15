package com.maniek.software.workoutnotepad.bodydimensions;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyDimensionsHeightRequest {

    @Min(value = 0, message = "Height cannot be lower than 0 cm")
    @Max(value = 270, message = "Height cannot be higher than 270 cm")
    private double height;
}
