package com.gialongchuai.shopapp.mappers;

import org.mapstruct.Mapper;

import com.gialongchuai.shopapp.dtos.request.RoleCreationRequest;
import com.gialongchuai.shopapp.dtos.response.RoleResponse;
import com.gialongchuai.shopapp.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreationRequest roleCreationRequest);

    RoleResponse toRoleResponse(Role role);
}
