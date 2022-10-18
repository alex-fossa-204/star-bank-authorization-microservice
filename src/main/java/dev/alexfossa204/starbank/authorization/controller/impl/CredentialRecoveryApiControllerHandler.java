package dev.alexfossa204.starbank.authorization.controller.impl;

import dev.alexfossa204.starbank.authorization.controller.CredentialRecoveryApi;
import dev.alexfossa204.starbank.authorization.controller.rest.VerificationCodeValidationRestTemplate;
import dev.alexfossa204.starbank.authorization.service.CredentialRecoveryService;
import dev.alexfossa204.starbank.authorization.service.exception.PasswordsDontMatchException;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeExpiredException;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeNotFoundException;
import dev.alexfossa204.starbank.authorization.service.impl.KafkaTopicProducerService;
import dev.alexfossa204.starbank.authorization.config.kafka.KafkaConstant;
import dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant;
import dev.alexfossa204.starbank.authorization.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class CredentialRecoveryApiControllerHandler implements CredentialRecoveryApi {

    private final CredentialRecoveryService credentialRecoveryService;

    private final KafkaTopicProducerService kafkaTopicProducerService;

    private final VerificationCodeValidationRestTemplate verificationCodeValidationRestTemplate;


    @Override
    public ResponseEntity<PassportVerificationResponseDto> passportVerificationPostRequest(PassportVerificationRequestDto passportVerificationRequestDto) {
        PassportVerificationResponseDto passportVerificationResult = credentialRecoveryService.verifyUserPassport(passportVerificationRequestDto);
        HttpStatus httpStatus = passportVerificationResult.getHttpStatus();
        return new ResponseEntity<>(passportVerificationResult, httpStatus);
    }

    @Override
    public ResponseEntity<HttpResponseDto> resetUserPasswordByVerificationCodePostRequest(PasswordRecoveryRequestByCodeDto credentialRecoveryDto) throws VerificationCodeNotFoundException, VerificationCodeExpiredException, PasswordsDontMatchException {
        UserDto userDto = credentialRecoveryService.findClientInfoByPassportNumber(credentialRecoveryDto.getPassportNumber());
        verificationCodeValidationRestTemplate.sendVerificationCodeValidationPostRequest(userDto.getPhoneLogin(), credentialRecoveryDto.getVerificationCode());
        validateCustomPassword(credentialRecoveryDto);
        kafkaTopicProducerService.publishVerificationCodeSetAsUsedTopicEvent(VerificationCodeSetAsUsedTopicMessage.builder()
                        .publicationTimeStamp(new Date())
                        .publicationReason(KafkaConstant.CLIENT_REGISTRATION_PUBLICATION_MESSAGE)
                        .phoneNumber(userDto.getPhoneLogin())
                        .verificationCode(credentialRecoveryDto.getVerificationCode())
                .build());
        HttpResponseDto httpResponseDto = credentialRecoveryService.resetCurrentUserPasswordByVerificationCode(credentialRecoveryDto);
        return new ResponseEntity<>(httpResponseDto, httpResponseDto.getHttpStatus());
    }

    private void validateCustomPassword(PasswordRecoveryRequestByCodeDto credentialRecoveryDto) throws PasswordsDontMatchException {
        String password = credentialRecoveryDto.getNewPassword();
        String confirmPassword = credentialRecoveryDto.getConfirmPassword();
        if(!StringUtils.equals(password, confirmPassword)) {
            throw new PasswordsDontMatchException(ServiceExceptionConstant.PASSWORDS_DONT_MATCH_MSG);
        }
    }

}
