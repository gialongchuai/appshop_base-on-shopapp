package com.gialongchuai.shopapp.services;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest userCreationRequest) {
        User user = userMapper.toUser(userCreationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role existingRole = roleRepository.findByName("USER");

        if (existingRole == null) {
            throw new AppException(UserErrorCode.ROLE_USER_NOT_EXISTED);
        }

        user.setRole(existingRole);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(UserErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser() {
        log.info("I am joining in method (getAllUser)!");
        // return userMapper.toUsersResponse(userRepository.findAll());
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserResponse getUser(String userId) {
        log.info("I am joining in method (getUser)!");
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED));
        log.info("=========USER: " + user.getRole());
        Role role = user.getRole();
        log.info("=========Role: " + role.getId());
        log.info("=========Role: " + role.getName());
        return userMapper.toUserResponse(user);
    }

    // chỉ cần kèm theo 1 token thì sẽ cho biết info người gửi thông qua token, từ name đó được lấy từ sub: ví dụ sub có
    // admin thì tìm username admin.
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();
        log.info("===== Name: " + name);
        return userMapper.toUserResponse(
                userRepository.findById(name).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED)));
    }

    @PostAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserResponse updateUser(String userId, UserUpdationRequest userUpdationRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, userUpdationRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(UserErrorCode.USER_NOT_EXISTED));
        user.setIsActive(false);
        userRepository.save(user);
        return "Delete user successfully!";
    }
}
