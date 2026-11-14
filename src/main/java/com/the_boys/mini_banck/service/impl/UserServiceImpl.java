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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO createUser(UserRequestDTO request) {
        Users user = userMapper.toEntity(request);
        user.setPassword( passwordEncoder.encode(user.getPassword()));
        return userMapper.toResponse(usersRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with", "id", id)
        );
        return userMapper.toResponse(users);
    }

    @Override
    public void updateUser(UserRequestDTO userRequest) {

    }

    @Override
    public void deleteUser(Long id) {

    }
}
