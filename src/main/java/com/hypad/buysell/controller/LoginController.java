package com.hypad.buysell.controller;

import com.hypad.buysell.model.User;
import com.hypad.buysell.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth/api/v1")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("name") String name,
                           @ModelAttribute("password") String password, Model model) {

        if(authService.ifUserExists(name,password)){
            return "redirect:/";
        }
        else{
            User user = new User();
            user.setName(name);
            user.setPassword(password);

            authService.saveUser(user);

            model.addAttribute("msg","User created Successfully");
            return "redirect:/";
        }
    }
}
