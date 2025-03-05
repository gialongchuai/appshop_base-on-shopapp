package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.dtos.request.RoleCreationRequest;
import com.gialongchuai.shopapp.dtos.response.RoleResponse;
import com.gialongchuai.shopapp.entities.Role;
import com.gialongchuai.shopapp.mappers.RoleMapper;
import com.gialongchuai.shopapp.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public RoleResponse createRole(RoleCreationRequest roleCreationRequest) {
        return roleMapper.toRoleResponse(roleRepository.save(roleMapper.toRole(roleCreationRequest)));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<RoleResponse> getAllRoles() {
        var role = roleRepository.findAll();
        return role.stream().map(roleMapper::toRoleResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
