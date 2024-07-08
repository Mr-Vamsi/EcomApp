package com.application.EcomApp.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class usersData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    public String username;
    @Column(name = "useremail")
    public String email;
    public String usermobile;
    public String userpassword;
}
