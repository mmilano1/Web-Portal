package com.markomilanovits.dataverseAssignment.mapper;

import com.markomilanovits.dataverseAssignment.domain.User;
import com.markomilanovits.dataverseAssignment.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserToUserModelMapper {

    public UserModel map(User user) {
        UserModel userModel = new UserModel();
        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());
        userModel.setPhone(user.getPhone());
        userModel.setEmail(user.getEmail());
        userModel.setCompany(user.getCompany());
        userModel.setPhotoPath(user.getPhotoPath());
        userModel.setId(user.getId());
        return userModel;
    }

}
