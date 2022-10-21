package dev.alexfossa204.starbank.microservice.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthorizationMicroserviceTestConstant {

    AUTH_TYPE_PHONE("phone"),

    DUMMY_TOKEN("asojfghosdjhgfopehg349863478964398ldhgfdhfgljhgsdlgh"),

    DUMMY_ROLE("DUMMY_ROLE"),
    AUTH_TYPE_PASSPORT("passport"),
    DUMMY_PHONE("375331001010"),
    DUMMY_PASSPORT("538767834UI09L"),

    DUMMY_CORRECT_PASSWORD("123456Dummy$");

    private String dummyValue;

}
