package com.the_boys.mini_banck.mapper;

import com.the_boys.mini_banck.dto.UserRequestDTO;
import com.the_boys.mini_banck.model.Users;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    Users toUserDTO(UserRequestDTO userRequestDTO);

    UserRequestDTO toUser(Users users);

}
