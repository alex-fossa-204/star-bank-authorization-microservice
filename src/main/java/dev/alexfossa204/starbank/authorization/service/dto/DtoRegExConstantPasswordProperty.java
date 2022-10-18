package dev.alexfossa204.starbank.authorization.service.dto;

interface DtoRegExConstantPasswordProperty {

    String PASSWORD_NOT_BLANK_VALIDATION_MESSAGE = "The password field cannot be blank";

    String PASSWORD_REGEX_VALIDATION_VALUE = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\[\\]\\/!\\?@#\\&$_\\*;%^()|<>,~`.=\\+}\\'{:\\-\\\\])[0-9A-Za-z\\[\\]\\/!\\?@#\\&$_\\*;%^()|<>,~`.=\\+}\\'{:\\-\\\\]{6,20}$";;

    String PASSWORD_REGEX_VALIDATION_MESSAGE = "The password must contain 6-20 characters: latin a-z, uppercase A-Z, and at least 1 special char [ ] / ! ? @ # & $ _ * ; % ^ ( ) | < > , ~ ` . = + } ' { : -";

}
