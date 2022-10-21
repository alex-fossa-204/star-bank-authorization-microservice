package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.service.dto.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> findAllRoles();

}
