package com.maniek.software.workoutnotepad.bodydimensions;

import com.maniek.software.workoutnotepad.workoutResult.WorkoutResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.Optional;
@AllArgsConstructor
public class CustomBodyDimensionsRepositoryImpl implements CustomBodyDimensionsRepository{

    private final EntityManager entityManager;

    @Override
    public Optional<BodyDimensions> findUserLatestBodyDimensions(String username) {
        try{
            TypedQuery<BodyDimensions> query = entityManager.createQuery(
                    "SELECT bd from BodyDimensions bd WHERE bd.user.username = :username ORDER BY bd.creationDate DESC LIMIT 1",
                    BodyDimensions.class
            );
            query.setParameter("username", username);

            BodyDimensions bodyDimensions = query.getSingleResult();

            return Optional.of(bodyDimensions);

        } catch (NoResultException e){
            return Optional.empty();
        }
    }
}
