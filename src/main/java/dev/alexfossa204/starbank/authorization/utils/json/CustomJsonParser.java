package dev.alexfossa204.starbank.authorization.utils.json;

import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeNotFoundException;

public interface CustomJsonParser<T> {

    T parseJsonString(String jsonString) throws VerificationCodeNotFoundException;

}
