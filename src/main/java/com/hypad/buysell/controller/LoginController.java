package com.hypad.buysell.controller;

import com.hypad.buysell.model.User;
import com.hypad.buysell.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth/api/v1")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("name") String name,
                           @ModelAttribute("password") String password,
                           @ModelAttribute("gmail") String gmail,
                           RedirectAttributes model)
    {
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setGmail(gmail);

                authService.saveUser(user);

                model.addAttribute("msg","User created Successfully");
                model.addAttribute("user",user);

                return "redirect:/userSuccessfullyPage";
    }
}
