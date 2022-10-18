package dev.alexfossa204.starbank.authorization.service;

import dev.alexfossa204.starbank.authorization.service.dto.*;

public interface CredentialRecoveryService {

    PassportVerificationResponseDto verifyUserPassport(PassportVerificationRequestDto passportVerificationRequestDto);

    HttpResponseDto resetCurrentUserPasswordByVerificationCode(PasswordRecoveryRequestByCodeDto credentialRecoveryDto);

    UserDto findClientInfoByPassportNumber(String passportNumber);

}
