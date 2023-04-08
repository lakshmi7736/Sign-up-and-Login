package com.web.userregistration.Controller;

import com.web.userregistration.Model.UserDtls;
import com.web.userregistration.Service.UserService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class homeController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping(value = "/signin")
    public String login(){
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(){
        return "register";
    }


@PostMapping(value = "/createUser")
    public String createUser(@ModelAttribute UserDtls user, HttpSession session){

        boolean f=userService.checkEmail(user.getEmail());
        if(f){
            session.setAttribute("msg","Email ID ALREADY EXIST!");
        }
        else {
            UserDtls userDtls= userService.createUser(user);
            if(userDtls!=null){
                session.setAttribute("msg","REGISTERED SUCCESSFULLY");
            }
            else {
                session.setAttribute("msg","SOMETHING ERROR ON SERVER");
            }
        }

        return "redirect:/register";
    }

}
