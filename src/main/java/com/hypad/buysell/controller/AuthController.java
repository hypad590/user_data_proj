package com.hypad.buysell.controller;

import com.hypad.buysell.model.User;
import com.hypad.buysell.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth/api/v1")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/authorization")
    public String authPage() {
        return "authPage";
    }

    @PostMapping("/authorization")
    public String authPage(@RequestParam String name, @RequestParam String password,
                           @RequestParam String gmail,
                           Model attributes) {
        if(authService.ifUserExists(name,password,gmail)){
            return "redirect:/";
        }
        else{
            attributes.addAttribute("name", name);
            attributes.addAttribute("password", password);
            attributes.addAttribute("gmail",gmail);

            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setGmail(gmail);

            authService.saveUser(user);

            attributes.addAttribute("msg","User created Successfully");
            attributes.addAttribute("user",user);

            return "redirect:/userSuccessfullyPage";
        }
    }

    @GetMapping("/userSuccessfullyPage")
    @ResponseBody
    public String userCreatedSuccessfully(@ModelAttribute("msg") String msg) {
        return msg;
    }

}
