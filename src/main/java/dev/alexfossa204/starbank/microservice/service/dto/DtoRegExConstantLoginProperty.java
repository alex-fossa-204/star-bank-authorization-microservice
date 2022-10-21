package dev.alexfossa204.starbank.microservice.service.dto;

interface DtoRegExConstantLoginProperty {

    String LOGIN_VALIDATION_REGEX_VALUE = "^[0-9A-Za-z]{2,30}$";

    String LOGIN_VALIDATION_REGEX_MESSAGE = "The login field must contain 2-30 chars, only latin chars A-Z, a-z and numerics in range 0-9";

}
