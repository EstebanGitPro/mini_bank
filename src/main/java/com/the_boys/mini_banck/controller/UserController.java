package com.the_boys.mini_banck.controller;


import com.the_boys.mini_banck.dto.UserResponseDTO;
import com.the_boys.mini_banck.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDTO getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

}
