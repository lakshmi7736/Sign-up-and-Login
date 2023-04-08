package com.web.userregistration.Repo;

import com.web.userregistration.Model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepo extends JpaRepository<UserDtls,Integer> {
        public boolean existsByEmail(String email);
        public UserDtls findByEmail(String email);
}
