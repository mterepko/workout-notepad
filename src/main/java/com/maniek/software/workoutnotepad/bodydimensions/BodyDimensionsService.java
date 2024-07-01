package com.maniek.software.workoutnotepad.bodydimensions;

import com.maniek.software.workoutnotepad.user.DuplicateUsersHeightException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class BodyDimensionsService {

    private final BodyDimensionsRepository bodyDimensionsRepository;

    public BodyDimensions findUsersLatestBodyDimensions(String username){

        return bodyDimensionsRepository.findUsersLatestBodyDimensions(username).orElse(null);
    }

    public List<BodyDimensions> findUsersBodyDimensions(String username) {

        return bodyDimensionsRepository.findAllByUserUsername(username);
    }

    public boolean hasUserBodyDimensions(String username){

        return !findUsersBodyDimensions(username).isEmpty();
    }

    public Double getUserHeight(String username){

        return bodyDimensionsRepository.findLatestHeightByUserUsername(username);
    }

    @Transactional
    public BodyDimensions saveBodyDimensions(BodyDimensionsRequest bodyDimensionsRequest, String username){

        Double userHeight = getUserHeight(username);
        if(userHeight != null){
            bodyDimensionsRequest.setHeight(userHeight);
        }
        return createBodyDimensions(bodyDimensionsRequest);
    }
    @Transactional
    public BodyDimensions createBodyDimensions(BodyDimensionsRequest bodyDimensionsRequest){

        BodyDimensions bodyDimensions = new BodyDimensions(bodyDimensionsRequest.getWeight(),
                bodyDimensionsRequest.getHeight(), bodyDimensionsRequest.getNeckSize(),
                bodyDimensionsRequest.getBicepsSize(), bodyDimensionsRequest.getChestSize(),
                bodyDimensionsRequest.getForearmSize(), bodyDimensionsRequest.getWaistSize(),
                bodyDimensionsRequest.getHipsSize(), bodyDimensionsRequest.getThighSize(),
                bodyDimensionsRequest.getCalfSize(), new Date());

        return bodyDimensionsRepository.save(bodyDimensions);
    }

    @Transactional
    public void updateHeight(String username, Double newHeight) {

        List<BodyDimensions> usersBodyDimensionsList = bodyDimensionsRepository.findAllByUserUsername(username);

        if(usersBodyDimensionsList.isEmpty()){
            throw new RuntimeException("No body measurements");
        }

        if(usersBodyDimensionsList.get(0).getHeight() == newHeight){
            throw new DuplicateUsersHeightException(String.format("Your height already is %,.2fcm",
                    newHeight));
        }

        for (BodyDimensions bodyDimensions : usersBodyDimensionsList){
            bodyDimensions.setHeight(newHeight);
        }

        bodyDimensionsRepository.saveAll(usersBodyDimensionsList);
    }

}
