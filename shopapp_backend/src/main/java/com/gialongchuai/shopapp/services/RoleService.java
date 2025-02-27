package com.gialongchuai.shopapp.services;

import com.gialongchuai.shopapp.dtos.request.RoleCreationRequest;
import com.gialongchuai.shopapp.entities.Role;
import com.gialongchuai.shopapp.repositories.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;

    public void create(RoleCreationRequest roleCreationRequest) {
        roleRepository.save(Role.builder()
                .name(roleCreationRequest.getName())
                .build());
    }
}
