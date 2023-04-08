package com.web.userregistration.Service;

import com.web.userregistration.Model.UserDtls;

public interface UserService {
    public UserDtls createUser(UserDtls user);
    public boolean checkEmail(String email);


}
