package com.markomilanovits.dataverseAssignment.mapper;

import com.markomilanovits.dataverseAssignment.domain.User;
import com.markomilanovits.dataverseAssignment.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserFormToUserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User map(UserForm userForm) {
        User newUser = new User();
        newUser.setFirstName(userForm.getFirstName());
        newUser.setLastName(userForm.getLastName());
        newUser.setPhone(userForm.getPhone());
        newUser.setEmail(userForm.getEmail());
        newUser.setCompany(userForm.getCompany());
        newUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
        return newUser;
    }

}
