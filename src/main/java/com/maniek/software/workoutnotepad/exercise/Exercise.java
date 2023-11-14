package com.maniek.software.workoutnotepad.exercise;

import com.maniek.software.workoutnotepad.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean hasReps;

    private boolean hasWeight;

    private boolean hasSeries;

    private boolean hasTime;

    private Date creationDate;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String description;

    public Exercise(String name, boolean hasReps, boolean hasWeight, boolean hasSeries, boolean hasTime, String description) {
        this.name = name;
        this.hasReps = hasReps;
        this.hasWeight = hasWeight;
        this.hasSeries = hasSeries;
        this.hasTime = hasTime;
        this.description = description;
        this.creationDate = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        if (hasReps != exercise.hasReps) return false;
        if (hasWeight != exercise.hasWeight) return false;
        if (hasSeries != exercise.hasSeries) return false;
        if (hasTime != exercise.hasTime) return false;
        return name.equals(exercise.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (hasReps ? 1 : 0);
        result = 31 * result + (hasWeight ? 1 : 0);
        result = 31 * result + (hasSeries ? 1 : 0);
        result = 31 * result + (hasTime ? 1 : 0);
        return result;
    }
}

