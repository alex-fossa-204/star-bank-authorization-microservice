package dev.alexfossa204.starbank.microservice.service.dto;

interface DtoRegExConstantPassportProperty {

    String PASSPORT_NUMBER_REGEX_VALIDATION_VALUE = "^[0-9A-Za-z]{2,30}$";

    String PASSPORT_NUMBER_REGEX_VALIDATION_MESSAGE = "The passport number must contain 2-30 chars, only latin chars A-Z, a-z and numerics in range 0-9";

}
