package com.maniek.software.workoutnotepad.bodydimensions;

import com.maniek.software.workoutnotepad.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class BodyDimensions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    private Date creationDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BodyDimensions( double weight, double height, double neckSize,
                          double bicepsSize, double chestSize, double forearmSize, double waistSize,
                          double hipsSize, double thighSize, double calfSize, Date creationDate) {

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
        this.creationDate = creationDate;
    }

}
