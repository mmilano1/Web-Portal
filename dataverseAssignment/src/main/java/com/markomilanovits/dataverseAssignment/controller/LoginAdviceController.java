package com.markomilanovits.dataverseAssignment.controller;

import com.markomilanovits.dataverseAssignment.model.UserModel;
import com.markomilanovits.dataverseAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice(basePackages = "com.markomilanovits.dataverseAssignment.controller")
public class LoginAdviceController {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void globalAttributes(Model model, HttpServletRequest request) {
        Optional<UserModel> loggedInUser = userService.findUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if (loggedInUser.isPresent()) {
            model.addAttribute("loggedInUser", loggedInUser.get());
        } else {
            model.addAttribute("loggedInUser", null);
        }
    }
}
