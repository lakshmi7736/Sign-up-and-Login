package com.web.userregistration.Controller;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.web.userregistration.Model.UserDtls;
import com.web.userregistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }




    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }



    @PostMapping(value = "/createUser")
    public String createUser(@ModelAttribute UserDtls user, HttpSession session){

        // Check if email already exists
        boolean emailExists = userService.isExistingEmail(user.getEmail());
        if(emailExists){
            session.setAttribute("msg","Email ID ALREADY EXISTS!");
            return "redirect:/register";
        }

        // Check if email is of valid type
        boolean validEmailType = userService.isValidEmail(user.getEmail());
        if(!validEmailType){
            session.setAttribute("msg","Invalid email format. Please enter a valid email address of type gmail.com");
            return "redirect:/register";
        }

        // Check if password is valid
        boolean validPassword = userService.checkPassword(user.getPassword());
        if(!validPassword){
            session.setAttribute("msg","Invalid password format. Password must be at least 6 characters long.");
            return "redirect:/register";
        }

        // Create user
        UserDtls userDtls = userService.createUser(user);
        if(userDtls != null){
            session.setAttribute("msg","REGISTERED SUCCESSFULLY");
        }
        else {
            session.setAttribute("msg","SOMETHING WENT WRONG. PLEASE TRY AGAIN LATER.");
        }

        return "redirect:/register";
    }

    @GetMapping("/signin")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Perform backend validation here and return appropriate view
        if (username.equals("admin") && password.equals("password")) {
            return "redirect:/user/";
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }


}