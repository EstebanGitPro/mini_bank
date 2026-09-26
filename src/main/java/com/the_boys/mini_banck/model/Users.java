package com.the_boys.mini_banck.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    private  long id;
    private   String name;
    private    String email;
    private String password;

}
