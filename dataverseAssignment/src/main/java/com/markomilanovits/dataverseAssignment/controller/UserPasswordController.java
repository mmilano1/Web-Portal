package com.markomilanovits.dataverseAssignment.controller;

import com.markomilanovits.dataverseAssignment.forms.UserPasswordForm;
import com.markomilanovits.dataverseAssignment.model.UserModel;
import com.markomilanovits.dataverseAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserPasswordController {

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/user/profile/changepassword")
    public String showUserChangePassword(Model model) {
        UserModel loggedInUser = (UserModel) model.getAttribute("loggedInUser");
        model.addAttribute("userModel", loggedInUser);
        UserPasswordForm userPasswordForm = new UserPasswordForm();
        userPasswordForm.setId(loggedInUser.getId());
        model.addAttribute("userForm", userPasswordForm);
        return "userChangePassword";
    }

    @PostMapping("/user/profile/changepassword")
    private String userChangePassword(Model model, @Valid @ModelAttribute("userForm") UserPasswordForm userPasswordForm, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        UserModel loggedInUser = (UserModel) model.getAttribute("loggedInUser");
        model.addAttribute("userModel", loggedInUser);
        if(bindingResult.hasErrors()){
            model.addAttribute("validationError","an error has occured");
            return "userChangePassword";
        }
        boolean flag = userService.updateUserPassword(userPasswordForm);
        if(flag){
            redirectAttributes.addFlashAttribute("redirectMessageSuccess", "Password updated successfully!");

            // update the Security's currently logged in user details
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            List<GrantedAuthority> updatedAuthorities = new ArrayList<>(auth.getAuthorities());
            updatedAuthorities.add(new SimpleGrantedAuthority("USER"));
            Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), passwordEncoder.encode(userPasswordForm.getPassword()), updatedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(newAuth);

        }else{
            redirectAttributes.addFlashAttribute("redirectMessageError", "Password update unsuccessful. Please try again.");
        }
        return "redirect:/user/profile";
    }

}
