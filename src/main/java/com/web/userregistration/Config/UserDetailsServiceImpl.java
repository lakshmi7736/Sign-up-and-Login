package com.web.userregistration.Config;

import com.web.userregistration.Model.UserDtls;
import com.web.userregistration.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   @Autowired
   private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDtls user=userRepo.findByEmail(email);
        if(user!=null){
            return new CustomerUserDetails(user);
        }

        throw new UsernameNotFoundException("user not available");
    }
}
