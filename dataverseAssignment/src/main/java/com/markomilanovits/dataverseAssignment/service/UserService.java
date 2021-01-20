package com.markomilanovits.dataverseAssignment.service;

import com.markomilanovits.dataverseAssignment.forms.UserForm;
import com.markomilanovits.dataverseAssignment.forms.UserPasswordForm;
import com.markomilanovits.dataverseAssignment.forms.UserPhotoForm;
import com.markomilanovits.dataverseAssignment.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String insertUser(UserForm userform);

    Optional<UserModel> findUserByEmail(String email);

    Optional<UserModel> findUser(Long id);

    String updateUser(UserModel userModel);

    boolean updateUserPassword(UserPasswordForm userPasswordForm);

    boolean updateUserPhoto(UserPhotoForm userPhotoForm);

}
