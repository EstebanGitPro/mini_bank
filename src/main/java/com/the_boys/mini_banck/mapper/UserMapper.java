package com.the_boys.mini_banck.mapper;

import com.the_boys.mini_banck.dto.UserRequestDTO;
import com.the_boys.mini_banck.dto.UserResponseDTO;
import com.the_boys.mini_banck.model.Users;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    Users toUserDTO(UserRequestDTO userRequestDTO);

    UserResponseDTO toUser(Users users);

}
