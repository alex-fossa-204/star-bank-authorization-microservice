package dev.alexfossa204.starbank.microservice.mapper.impl;

import dev.alexfossa204.starbank.microservice.config.security.user.JwtUserDetails;
import dev.alexfossa204.starbank.microservice.mapper.EntityToDtoMapper;
import dev.alexfossa204.starbank.microservice.repository.model.Credential;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CredentialToJwtUserDetailsMapper extends EntityToDtoMapper<Credential, JwtUserDetails> {

    @Mapping(target = "password", source = "password")
    @Mapping(target = "isNonLocked", source = "isNonLocked")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "isCredentialNonExpired", source = "isCredentialNonExpired")
    @Mapping(target = "role", source = "user.role")
    @Override
    JwtUserDetails mapEntityToDto(Credential entity);
}
