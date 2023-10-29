package com.maniek.software.workoutnotepad.bodydimensions;

import com.maniek.software.workoutnotepad.user.User;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class BodyDimensions {

    @Id
    private Long id;

    private String gender;

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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BodyDimensions(String gender, double weight, double height, double neckSize,
                          double bicepsSize, double chestSize, double forearmSize, double waistSize,
                          double hipsSize, double thighSize, double calfSize, User user) {
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.neckSize = neckSize;
        this.bicepsSize = bicepsSize;
        this.chestSize = chestSize;
        this.forearmSize = forearmSize;
        this.waistSize = waistSize;
        this.hipsSize = hipsSize;
        this.thighSize = thighSize;
        this.calfSize = calfSize;
        this.user = user;
    }

}
