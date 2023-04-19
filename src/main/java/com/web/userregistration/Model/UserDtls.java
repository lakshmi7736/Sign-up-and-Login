package com.web.userregistration.Model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class UserDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String password;

    private String role;
}