package com.the_boys.mini_banck.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private  String name;

    @NotBlank(message = "The password is required")
    private   String password;

    @Email(message = "Check the correct email format")
    private   String email;

}
