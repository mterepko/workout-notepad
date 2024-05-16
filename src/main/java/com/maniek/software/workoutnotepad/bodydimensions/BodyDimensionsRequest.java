package com.maniek.software.workoutnotepad.bodydimensions;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BodyDimensionsRequest {

    @Min(value = 0, message = "Weight cannot be lower than 0 kg")
    @Max(value = 300, message = "Weight cannot be higher than 300 kg")
    private double weight;

    @Min(value = 0, message = "Height cannot be lower than 0 cm")
    @Max(value = 270, message = "Height cannot be higher than 270 cm")
    private double height;

    @Min(value = 0, message = "Neck size cannot be lower than 0 cm")
    @Max(value = 100, message = "Neck size cannot be higher than 100 cm")
    private double neckSize;

    @Min(value = 0, message = "Biceps size cannot be lower than 0 cm")
    @Max(value = 100, message = "Biceps size cannot be higher than 100 cm")
    private double bicepsSize;

    @Min(value = 0, message = "Chest size cannot be lower than 0 cm")
    @Max(value = 250, message = "Chest size cannot be higher than 250 cm")
    private double chestSize;

    @Min(value = 0, message = "Forearm size cannot be lower than 0 cm")
    @Max(value = 100, message = "Forearm size cannot be higher than 100 cm")
    private double forearmSize;

    @Min(value = 0, message = "Waist size cannot be lower than 0 cm")
    @Max(value = 250, message = "Waist size cannot be higher than 250 cm")
    private double waistSize;

    @Min(value = 0, message = "Hips size cannot be lower than 0 cm")
    @Max(value = 250, message = "Hips size cannot be higher than 250 cm")
    private double hipsSize;

    @Min(value = 0, message = "Thigh size cannot be lower than 0 cm")
    @Max(value = 150, message = "Thigh size cannot be higher than 150 cm")
    private double thighSize;

    @Min(value = 0, message = "Calf size cannot be lower than 0 cm")
    @Max(value = 100, message = "Calf size cannot be higher than 100 cm")
    private double calfSize;
}
