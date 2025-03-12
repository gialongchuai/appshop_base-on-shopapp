package com.gialongchuai.shopapp.services;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.gialongchuai.shopapp.dtos.request.RoleCreationRequest;
import com.gialongchuai.shopapp.dtos.response.RoleResponse;
import com.gialongchuai.shopapp.mappers.RoleMapper;
import com.gialongchuai.shopapp.repositories.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService implements IRoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public RoleResponse createRole(RoleCreationRequest roleCreationRequest) {
        return roleMapper.toRoleResponse(roleRepository.save(roleMapper.toRole(roleCreationRequest)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<RoleResponse> getAllRoles() {
        var role = roleRepository.findAll();
        return role.stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public String deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
        return "Delete role successfully!";
    }
}
