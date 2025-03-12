package com.gialongchuai.shopapp.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.gialongchuai.shopapp.dtos.request.UserCreationRequest;
import com.gialongchuai.shopapp.dtos.request.UserUpdationRequest;
import com.gialongchuai.shopapp.dtos.response.UserResponse;
import com.gialongchuai.shopapp.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleResponse", source = "role")
    UserResponse toUserResponse(User user);

    User toUser(UserCreationRequest userCreationRequest);

    void updateUser(@MappingTarget User user, UserUpdationRequest userUpdationRequest);
}
