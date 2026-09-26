package com.the_boys.mini_banck.service;

import com.the_boys.mini_banck.dto.UserRequestDTO;
import com.the_boys.mini_banck.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequest);
    UserRequestDTO getUserById(Long id);
    void  updateUser(UserRequestDTO userRequest);
    void deleteUser(Long id);

}
