package com.gialongchuai.shopapp.services;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IUserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gialongchuai.shopapp.dtos.request.UserCreationRequest;
import com.gialongchuai.shopapp.dtos.request.UserUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.UserResponse;
import com.gialongchuai.shopapp.entities.Role;
import com.gialongchuai.shopapp.entities.User;
import com.gialongchuai.shopapp.exceptions.UserErrorCode;
import com.gialongchuai.shopapp.exceptions.custom.AppException;
import com.gialongchuai.shopapp.mappers.UserMapper;
import com.gialongchuai.shopapp.repositories.RoleRepository;
import com.gialongchuai.shopapp.repositories.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    @Override
    public UserResponse createUser(UserCreationRequest userCreationRequest) {
        User user = userMapper.toUser(userCreationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role existingRole = roleRepository.findByName("USER");

        if (existingRole == null) {
            throw new AppException(UserErrorCode.ROLE_USER_NOT_EXISTED);
        }

        user.setRole(existingRole);
        user.setIsActive(true);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(UserErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<UserResponse> getAllUser() {
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Override
    public UserResponse getUser(String userId) {
        log.info("I am joining in method (getUser)!");
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED));
        Role role = user.getRole();
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();
        log.info("===== Name: " + name);
        return userMapper.toUserResponse(
                userRepository.findById(name).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED)));
    }

    @PostAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Override
    public UserResponse updateUser(String userId, UserUpdationRequest userUpdationRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, userUpdationRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public String deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED));
        user.setIsActive(false);
        userRepository.save(user);
        return "Delete user successfully!";
    }
}
