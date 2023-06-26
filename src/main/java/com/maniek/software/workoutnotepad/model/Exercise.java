package com.maniek.software.workoutnotepad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Exercise {

    @Id
    private Long id;

    private String name;

    private int countOfReps;

    private boolean hasReps;

    private double weight;

    private boolean hasWeight;

    private int countOfSeries;

    private int timeOfExerciseSeconds;

    private boolean hasTime;

    private int pauseBetweenSeriesSeconds;

    private boolean hasPause;

    private String description;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfReps() {
        return countOfReps;
    }

    public void setCountOfReps(int countOfReps) {
        this.countOfReps = countOfReps;
    }

    public boolean isHasReps() {
        return hasReps;
    }

    public void setHasReps(boolean hasReps) {
        this.hasReps = hasReps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isHasWeight() {
        return hasWeight;
    }

    public void setHasWeight(boolean hasWeight) {
        this.hasWeight = hasWeight;
    }

    public int getCountOfSeries() {
        return countOfSeries;
    }

    public void setCountOfSeries(int countOfSeries) {
        this.countOfSeries = countOfSeries;
    }

    public int getTimeOfExerciseSeconds() {
        return timeOfExerciseSeconds;
    }

    public void setTimeOfExerciseSeconds(int timeOfExerciseSeconds) {
        this.timeOfExerciseSeconds = timeOfExerciseSeconds;
    }

    public boolean isHasTime() {
        return hasTime;
    }

    public void setHasTime(boolean hasTime) {
        this.hasTime = hasTime;
    }

    public int getPauseBetweenSeriesSeconds() {
        return pauseBetweenSeriesSeconds;
    }

    public void setPauseBetweenSeriesSeconds(int pauseBetweenSeriesSeconds) {
        this.pauseBetweenSeriesSeconds = pauseBetweenSeriesSeconds;
    }

    public boolean isHasPause() {
        return hasPause;
    }

    public void setHasPause(boolean hasPause) {
        this.hasPause = hasPause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
