package com.furama.furama.Controllers;

import com.furama.furama.Models.User;
import com.furama.furama.Services.RoleServices;
import com.furama.furama.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@SessionAttributes("userInformation")
public class UserController {
    @Autowired
    UserServices userServices;
    @Autowired
    RoleServices roleServices;

    @GetMapping("/register-user")
   public String getRegisterUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roleList",roleServices.findAll());
        return "register";
    }

    @PostMapping("/register-user")
    public String postRegisterUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        userServices.validate(user,result);
        if (result.hasFieldErrors()) {
            return "register";
        }
        userServices.save(user);
        model.addAttribute("userInformation", user);
        model.addAttribute("message","Welcome " + user.getUsername());
        return "main";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/checklogin")
    public String postLogin(Principal principal, Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
        model.addAttribute("userInformation", user);
        model.addAttribute("message","Welcome " + user.getUsername());
        return "main";
    }
}
