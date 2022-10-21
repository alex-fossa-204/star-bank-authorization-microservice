package dev.alexfossa204.starbank.microservice.service.impl;

import dev.alexfossa204.starbank.microservice.service.dto.RoleResponseDto;
import dev.alexfossa204.starbank.microservice.repository.model.Role;
import dev.alexfossa204.starbank.microservice.repository.RoleRepository;
import dev.alexfossa204.starbank.microservice.service.RoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static dev.alexfossa204.starbank.microservice.service.impl.RoleServiceImpl.RoleServiceImplConstant.*;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleResponseDto> findAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(role -> RoleResponseDto.builder()
                        .timeStamp(new Date())
                        .httpStatus(OK)
                        .message(ROLE_OBJECT_EXTRACTED_SUCCESSFULLY_MESSAGE)
                        .roleName(role.getRoleName())
                        .privileges(
                                role.getPrivileges().stream()
                                        .map(privilege -> privilege.getPrivilegeName())
                                        .collect(Collectors.toList()))
                        .build()
                ).collect(Collectors.toList());
    }

    interface RoleServiceImplConstant {

        String ROLE_OBJECT_EXTRACTED_SUCCESSFULLY_MESSAGE = "Role object extracted successfully";

    }
}
