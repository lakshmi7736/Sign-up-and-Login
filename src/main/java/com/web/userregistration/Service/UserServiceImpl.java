package com.web.userregistration.Service;

import com.web.userregistration.Model.UserDtls;
import com.web.userregistration.repository.UserRepository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;


    @Override
    public UserDtls createUser(UserDtls user) {

        user.setPassword(passwordEncode.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        return userRepo.save(user);
    }
    public boolean checkPassword(String password) {
        return password.length() >= 6;
    }
    @Override
    public boolean isExistingEmail(String email) {
        // Check if email already exists
        return userRepo.existsByEmail(email);
    }

    public boolean isValidEmail(String email) {
        // Define the pattern to match a valid email address
        String emailRegex = "^[\\w\\.-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the given email address with the pattern
        Matcher matcher = pattern.matcher(email);

        // Return true if the email address matches the pattern, false otherwise
        return matcher.matches();
    }


}
