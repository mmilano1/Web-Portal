package com.markomilanovits.dataverseAssignment.controller;

import com.markomilanovits.dataverseAssignment.forms.UserPasswordForm;
import com.markomilanovits.dataverseAssignment.forms.UserPhotoForm;
import com.markomilanovits.dataverseAssignment.model.UserModel;
import com.markomilanovits.dataverseAssignment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserPhotoController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/profile/changephoto")
    public String showUserChangePassword(Model model) {
        UserModel loggedInUser = (UserModel) model.getAttribute("loggedInUser");
        model.addAttribute("userModel", loggedInUser);
        UserPhotoForm userPhotoForm = new UserPhotoForm();
        userPhotoForm.setId(loggedInUser.getId());
        model.addAttribute("userForm", userPhotoForm);
        return "userChangePhoto";
    }


    @PostMapping("/user/profile/changephoto")
    public String uploadImage(Model model, @RequestParam("imageFile") MultipartFile imageFile, RedirectAttributes redirectAttributes) {
        UserModel loggedInUser = (UserModel) model.getAttribute("loggedInUser");
        model.addAttribute("userModel", loggedInUser);

        UserPhotoForm userPhotoForm = new UserPhotoForm();
        userPhotoForm.setId(loggedInUser.getId());

        if(imageFile.isEmpty()){
            redirectAttributes.addFlashAttribute("redirectMessageError", "Please upload an image file.");
            return "redirect:/user/profile/changephoto";
        }

        try {
            userPhotoForm.setPhotoPath(imageFile.getOriginalFilename());

            Path currentPath = Paths.get(".");
            Path absolutePath = currentPath.toAbsolutePath();
            userPhotoForm.setPhotoPath(absolutePath + "/src/main/resources/static/photos/");

            Path path = Paths.get(userPhotoForm.getPhotoPath() + imageFile.getOriginalFilename());
            byte[] bytes = imageFile.getBytes();
            Files.write(path, bytes);

            userPhotoForm.setPhotoPath("/photos/" + imageFile.getOriginalFilename());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("redirectMessageError", "Photo update unsuccessful. Please try again.");
            return "redirect:/user/profile";
        }
        boolean flag = userService.updateUserPhoto(userPhotoForm);
        if (flag) {
            redirectAttributes.addFlashAttribute("redirectMessageSuccess", "Photo updated successfully. You look great!");
        } else {
            redirectAttributes.addFlashAttribute("redirectMessageError", "Photo update unsuccessful. Please try again.");
        }
        return "redirect:/user/profile";
    }

}
