package com.gialongchuai.shopapp.services.impl;

import com.gialongchuai.shopapp.dtos.request.RoleCreationRequest;
import com.gialongchuai.shopapp.dtos.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse createRole(RoleCreationRequest roleCreationRequest);
    List<RoleResponse> getAllRoles();
    String deleteRole(String roleId);
}
