package dev.alexfossa204.starbank.microservice.utils.json;

import dev.alexfossa204.starbank.microservice.service.exception.VerificationCodeNotFoundException;

public interface CustomJsonParser<T> {

    T parseJsonString(String jsonString) throws VerificationCodeNotFoundException;

}
