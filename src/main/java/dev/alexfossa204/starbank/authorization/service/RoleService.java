package dev.alexfossa204.starbank.authorization.service;

import dev.alexfossa204.starbank.authorization.service.dto.RoleResponseDto;

import java.util.List;

public interface RoleService {

    List<RoleResponseDto> findAllRoles();

}
