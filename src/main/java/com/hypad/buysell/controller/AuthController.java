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
    public ResponseEntity<String> authPage(@RequestBody User user,
                           Model attributes){
        if(authService.ifUserExists(user.getName(),user.getPassword(),user.getGmail())){
            return ResponseEntity.ok("/");
        }
        else{
            attributes.addAttribute("name",user.getName());
            attributes.addAttribute("password", user.getPassword());
            attributes.addAttribute("gmail",user.getGmail());

            authService.saveUser(user);

            attributes.addAttribute("msg","User created Successfully");
            attributes.addAttribute("user",user);

            return ResponseEntity.ok("/userSuccessfullyPage");
        }
    }

    @GetMapping("/userSuccessfullyPage")
    @ResponseBody
    public String userCreatedSuccessfully(@ModelAttribute("msg") String msg) {
        return msg;
    }

}
