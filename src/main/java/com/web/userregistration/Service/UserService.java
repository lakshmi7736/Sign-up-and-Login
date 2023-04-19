package com.web.userregistration.Service;


import com.web.userregistration.Model.UserDtls;

public interface UserService {

    public UserDtls createUser(UserDtls user);

    public boolean isExistingEmail(String email);
    public boolean checkPassword(String password);
    public boolean isValidEmail(String email);

}