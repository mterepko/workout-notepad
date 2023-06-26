package com.maniek.software.workoutnotepad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class BodyDimensions {

    @Id
    private Long id;

    @OneToOne
    private User user;

    private double weight;

    private double height;

    private double neckSize;

    private double bicepsSize;

    private double chestSize;

    private double forearmSize;

    private double waistSize;

    private double hipsSize;

    private double thighSize;

    private double calfSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getNeckSize() {
        return neckSize;
    }

    public void setNeckSize(double neckSize) {
        this.neckSize = neckSize;
    }

    public double getBicepsSize() {
        return bicepsSize;
    }

    public void setBicepsSize(double bicepsSize) {
        this.bicepsSize = bicepsSize;
    }

    public double getChestSize() {
        return chestSize;
    }

    public void setChestSize(double chestSize) {
        this.chestSize = chestSize;
    }

    public double getForearmSize() {
        return forearmSize;
    }

    public void setForearmSize(double forearmSize) {
        this.forearmSize = forearmSize;
    }

    public double getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(double waistSize) {
        this.waistSize = waistSize;
    }

    public double getHipsSize() {
        return hipsSize;
    }

    public void setHipsSize(double hipsSize) {
        this.hipsSize = hipsSize;
    }

    public double getThighSize() {
        return thighSize;
    }

    public void setThighSize(double thighSize) {
        this.thighSize = thighSize;
    }

    public double getCalfSize() {
        return calfSize;
    }

    public void setCalfSize(double calfSize) {
        this.calfSize = calfSize;
    }
}
