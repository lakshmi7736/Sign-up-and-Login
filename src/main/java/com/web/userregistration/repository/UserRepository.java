package com.web.userregistration.repository;

import com.web.userregistration.Model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDtls, Integer> {

    public UserDtls findByEmail(String email);
    public Boolean existsByEmail(String email);

}