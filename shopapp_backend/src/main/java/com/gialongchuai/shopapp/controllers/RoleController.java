package com.gialongchuai.shopapp.controllers;

import java.util.List;

import com.gialongchuai.shopapp.services.impl.IRoleService;
import org.springframework.web.bind.annotation.*;

import com.gialongchuai.shopapp.dtos.request.RoleCreationRequest;
import com.gialongchuai.shopapp.dtos.response.ApiResponse;
import com.gialongchuai.shopapp.dtos.response.RoleResponse;
import com.gialongchuai.shopapp.services.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/roles")
public class RoleController {
    IRoleService iRoleService;

    @PostMapping
    ApiResponse<RoleResponse> createRole(@RequestBody RoleCreationRequest roleCreationRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(iRoleService.createRole(roleCreationRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAllRole() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(iRoleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{roleId}")
    ApiResponse<RoleResponse> deleteRole(@PathVariable String roleId) {
        return ApiResponse.<RoleResponse>builder()
                .message(iRoleService.deleteRole(roleId))
                .build();
    }
}
