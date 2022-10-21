package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.service.dto.*;

public interface CredentialRecoveryService {

    PassportVerificationResponseDto verifyUserPassport(PassportVerificationRequestDto passportVerificationRequestDto);

    HttpResponseDto resetCurrentUserPasswordByVerificationCode(PasswordRecoveryRequestByCodeDto credentialRecoveryDto);

    UserDto findClientInfoByPassportNumber(String passportNumber);

}
