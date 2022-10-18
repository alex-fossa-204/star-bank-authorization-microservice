package dev.alexfossa204.starbank.authorization.controller;

import dev.alexfossa204.starbank.authorization.config.swagger.annotation.SwaggerOperationPassportVerificationPostRequest;
import dev.alexfossa204.starbank.authorization.config.swagger.annotation.SwaggerOperationResetUserPasswordByVerificationCodePostRequest;
import dev.alexfossa204.starbank.authorization.service.dto.HttpResponseDto;
import dev.alexfossa204.starbank.authorization.service.dto.PassportVerificationRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.PassportVerificationResponseDto;
import dev.alexfossa204.starbank.authorization.service.dto.PasswordRecoveryRequestByCodeDto;
import dev.alexfossa204.starbank.authorization.service.exception.ServiceException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Tag(name = "Credential recovery management API", description = "This Component is responsible for user credential recovery and activate user account if it was blocked (2^31-1 attempts).")
@RequestMapping(value = "/star-bank/recovery-management")
public interface CredentialRecoveryApi {

    @SwaggerOperationPassportVerificationPostRequest
    @RequestMapping(value = "/password-recovery/verification/credentials/passport", method = RequestMethod.POST)
    ResponseEntity<PassportVerificationResponseDto> passportVerificationPostRequest(@RequestBody @Valid PassportVerificationRequestDto passportVerificationRequestDto);

    @SwaggerOperationResetUserPasswordByVerificationCodePostRequest
    @RequestMapping(value = "/password-recovery/by-passport/by-code", method = RequestMethod.POST)
    ResponseEntity<HttpResponseDto> resetUserPasswordByVerificationCodePostRequest(@RequestBody @Valid PasswordRecoveryRequestByCodeDto credentialRecoveryDto) throws ServiceException;

}