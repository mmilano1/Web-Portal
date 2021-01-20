package com.markomilanovits.dataverseAssignment.controller;

import com.markomilanovits.dataverseAssignment.captcha.CaptchaService;
import com.markomilanovits.dataverseAssignment.forms.UserForm;
import com.markomilanovits.dataverseAssignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;

    @Autowired
    CaptchaService captchaService;

    @GetMapping("/register")
    private String registerUser(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    private String registerUser(Model model, @Valid @ModelAttribute("userForm") UserForm userform,
                                @RequestParam(name = "g-recaptcha-response") String recaptchaResponse,
                                HttpServletRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String ip = request.getRemoteAddr();
        boolean captchaVerifyFlag = captchaService.verifyRecaptcha(ip, recaptchaResponse);
        if (!captchaVerifyFlag) {
            redirectAttributes.addFlashAttribute("redirectMessageError", "Not verified by CAPTCHA");
            return "redirect:/register";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("userForm", userform);
            return "register";
        }
        String insertUserResult = userService.insertUser(userform);
        if (insertUserResult.isEmpty()) {
            redirectAttributes.addFlashAttribute("redirectMessageSuccess", "Registered successfully.");
        } else if (insertUserResult.equals("emailAlreadyTaken")) {
            redirectAttributes.addFlashAttribute("redirectMessageError", "Email already registered. Please log in instead.");
        } else {
            redirectAttributes.addFlashAttribute("redirectMessageError", insertUserResult);
        }

        return "redirect:/login";
    }

}
