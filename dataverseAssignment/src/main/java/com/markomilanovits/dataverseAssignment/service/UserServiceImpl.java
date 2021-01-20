package com.markomilanovits.dataverseAssignment.service;

import com.markomilanovits.dataverseAssignment.domain.User;
import com.markomilanovits.dataverseAssignment.forms.UserForm;
import com.markomilanovits.dataverseAssignment.forms.UserPasswordForm;
import com.markomilanovits.dataverseAssignment.forms.UserPhotoForm;
import com.markomilanovits.dataverseAssignment.mapper.UserFormToUserMapper;
import com.markomilanovits.dataverseAssignment.mapper.UserToUserModelMapper;
import com.markomilanovits.dataverseAssignment.model.UserModel;
import com.markomilanovits.dataverseAssignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFormToUserMapper userFormToUserMapper;

    @Autowired
    private UserToUserModelMapper userToUserModelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String insertUser(UserForm userform) {
        try {
            if (userRepository.findByEmail(userform.getEmail()).isPresent()) {
                return "emailAlreadyTaken";
            }
            userRepository.save(userFormToUserMapper.map(userform));
            return "";
        } catch (Exception ex) {
            return "Error: "+ex.getMessage();
        }
    }

    @Override
    public Optional<UserModel> findUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .map(user -> userToUserModelMapper.map(user));
    }

    @Override
    public Optional<UserModel> findUser(Long id) {
        return userRepository
                .findById(id)
                .map(user -> userToUserModelMapper.map(user));
    }

    @Override
    public String updateUser(UserModel userModel) {
        try{
            User originalUser = userRepository.findById(userModel.getId()).get();
            if (!originalUser.getEmail().equals(userModel.getEmail())) {
                if (userRepository.findByEmail(userModel.getEmail()).isPresent()) {
                    return "emailAlreadyTaken";
                }
            }
            originalUser.setFirstName(userModel.getFirstName());
            originalUser.setLastName(userModel.getLastName());
            originalUser.setPhone(userModel.getPhone());
            originalUser.setEmail(userModel.getEmail());
            originalUser.setCompany(userModel.getCompany());
            userRepository.save(originalUser);
            return "";
        }catch (Exception ex) {
            return "Error: "+ex.getMessage();
        }
    }

    @Override
    public boolean updateUserPassword(UserPasswordForm userPasswordForm) {
        try {
            User originalUser = userRepository.findById(userPasswordForm.getId()).get();
            originalUser.setPassword(passwordEncoder.encode(userPasswordForm.getPassword()));
            userRepository.save(originalUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUserPhoto(UserPhotoForm userPhotoForm) {
        try {
            User originalUser = userRepository.findById(userPhotoForm.getId()).get();
            originalUser.setPhotoPath(userPhotoForm.getPhotoPath());
            userRepository.save(originalUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
