package com.maniek.software.workoutnotepad.exercise;

public record ExerciseRequest(String name, boolean hasReps, boolean hasWeight, boolean hasSeries,
                              boolean hasTime, String description) {
}
