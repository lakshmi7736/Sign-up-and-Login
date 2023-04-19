package com.web.userregistration.Controller;


import java.security.Principal;

import com.web.userregistration.Model.UserDtls;
import com.web.userregistration.Service.UserService;
import com.web.userregistration.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("user", user);
        return "home";
    }


    @Autowired
    private UserRepository userRepo;

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email = p.getName();
        UserDtls user = userRepo.findByEmail(email);

        m.addAttribute("user", user);
    }

    @GetMapping("/")
    public String home() {
        return "user/home";
    }

}
