package dev.alexfossa204.starbank.authorization.mapper;

public interface EntityToDtoMapper <E, D> {

    D mapEntityToDto(E entity);

}
