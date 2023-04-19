package com.web.userregistration.Controller;
import javax.servlet.http.HttpSession;

import com.web.userregistration.Model.UserDtls;
import com.web.userregistration.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }




    @GetMapping(value = "/signin")
    public String login() {
        return "login";
    }




    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }



    @PostMapping(value = "/createUser")
    public String createUser(@ModelAttribute UserDtls user, HttpSession session){
        // Check if any fields are empty
        if(user.getEmail().isEmpty() || user.getPassword().isEmpty() || user.getFullName().isEmpty()){
            session.setAttribute("msg","Please fill out all fields.");
            return "redirect:/register";
        }

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
            session.setAttribute("msg","Invalid password format. Password must be at least 8 characters long.");
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









}