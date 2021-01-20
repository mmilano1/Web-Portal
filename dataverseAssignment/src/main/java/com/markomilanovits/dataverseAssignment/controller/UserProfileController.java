package com.markomilanovits.dataverseAssignment.controller;

import com.markomilanovits.dataverseAssignment.forms.UserForm;
import com.markomilanovits.dataverseAssignment.forms.UserPasswordForm;
import com.markomilanovits.dataverseAssignment.model.UserLoginDetails;
import com.markomilanovits.dataverseAssignment.model.UserModel;
import com.markomilanovits.dataverseAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserProfileController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/profile")
    public String showUserProfile(Model model) {
        UserModel loggedInUser = (UserModel) model.getAttribute("loggedInUser");
        model.addAttribute("userModel", loggedInUser);
        return "userProfile";
    }

    @PostMapping("/user/edit")
    private String userEdit(Model model, @Valid @ModelAttribute("userModel") UserModel userModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("validationError", "an error has occured");
            return "userProfile";
        }
        String userUpdatedResult = userService.updateUser(userModel);
        if (userUpdatedResult.isEmpty()) {
            redirectAttributes.addFlashAttribute("redirectMessageSuccess", "Details updated successfully!");

            // update the Security's currently logged in user details
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
            updatedAuthorities.add(new SimpleGrantedAuthority("USER"));
            Authentication newAuth = new UsernamePasswordAuthenticationToken(userModel.getEmail(), auth.getCredentials(), updatedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(newAuth);

        } else if (userUpdatedResult.equals("emailAlreadyTaken")) {
            redirectAttributes.addFlashAttribute("redirectMessageError", "Email already taken. Please try a different email.");
        } else {
            redirectAttributes.addFlashAttribute("redirectMessageError", "Error: "+ userUpdatedResult);
        }
        return "redirect:/user/profile";
    }

}
