package com.the_boys.mini_banck.service.impl;

import com.the_boys.mini_banck.dto.UserRequestDTO;
import com.the_boys.mini_banck.dto.UserResponseDTO;
import com.the_boys.mini_banck.exception.ResourceNotFoundException;
import com.the_boys.mini_banck.mapper.UserMapper;
import com.the_boys.mini_banck.model.Users;
import com.the_boys.mini_banck.repository.UsersRepository;
import com.the_boys.mini_banck.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public UserRequestDTO getUserById(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with", "id", id)
        );
        return userMapper.toUser(users);
    }

    @Override
    public void updateUser(UserRequestDTO userRequest) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
