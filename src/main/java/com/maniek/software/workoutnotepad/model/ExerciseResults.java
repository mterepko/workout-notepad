package com.maniek.software.workoutnotepad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ExerciseResults {

    @Id
    private Long id;

    private int countOfReps;

    private double weight;

    private int countOfSeries;

    private int timeOfExerciseSeconds;

    private int pauseBetweenSeriesSeconds;
    @ManyToOne
    private Exercise exercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCountOfReps() {
        return countOfReps;
    }

    public void setCountOfReps(int countOfReps) {
        this.countOfReps = countOfReps;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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

    public int getPauseBetweenSeriesSeconds() {
        return pauseBetweenSeriesSeconds;
    }

    public void setPauseBetweenSeriesSeconds(int pauseBetweenSeriesSeconds) {
        this.pauseBetweenSeriesSeconds = pauseBetweenSeriesSeconds;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
