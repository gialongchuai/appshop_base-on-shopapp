package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.UserCreationRequest;
import com.gialongchuai.shopapp.dtos.request.UserUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.UserResponse;

import java.util.List;

public interface IUserService {
    UserResponse createUser(UserCreationRequest userCreationRequest);
    List<UserResponse> getAllUser();
    UserResponse getUser(String userId);
    UserResponse getMyInfo();
    UserResponse updateUser(String userId, UserUpdationRequest userUpdationRequest);
    String deleteUser(String userId);
}
