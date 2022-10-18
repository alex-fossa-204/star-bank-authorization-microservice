package dev.alexfossa204.starbank.authorization.mapper.impl;

import dev.alexfossa204.starbank.authorization.mapper.EntityToDtoMapper;
import dev.alexfossa204.starbank.authorization.service.dto.UserDto;
import dev.alexfossa204.starbank.authorization.repository.model.Credential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CredentialToUserDtoMapper extends EntityToDtoMapper <Credential, UserDto> {

    @Mapping(target = "firstName", source = "user.passport.firstname")
    @Mapping(target = "lastName", source = "user.passport.lastname")
    @Mapping(target = "phoneLogin", source = "phoneLogin")
    @Mapping(target = "passportLogin", source = "passportLogin")
    @Mapping(target = "isClient", defaultValue = "false", ignore = true)
    @Override
    UserDto mapEntityToDto(Credential entity);
}
