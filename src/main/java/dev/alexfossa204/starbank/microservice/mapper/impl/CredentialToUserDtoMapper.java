package dev.alexfossa204.starbank.microservice.mapper.impl;

import dev.alexfossa204.starbank.microservice.mapper.EntityToDtoMapper;
import dev.alexfossa204.starbank.microservice.service.dto.UserDto;
import dev.alexfossa204.starbank.microservice.repository.model.Credential;
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
