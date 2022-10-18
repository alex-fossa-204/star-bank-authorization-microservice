package dev.alexfossa204.starbank.authorization.controller.impl;

import dev.alexfossa204.starbank.authorization.controller.VerificationApi;
import dev.alexfossa204.starbank.authorization.service.dto.UserDto;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationByPassportRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationResponseDto;
import dev.alexfossa204.starbank.authorization.service.CredentialRecoveryService;
import dev.alexfossa204.starbank.authorization.service.VerificationCodeService;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeNotGeneratedException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static dev.alexfossa204.starbank.authorization.controller.impl.VerificationApiControllerHandler.VerificationCodeApiConstant.*;
import static dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant.*;

@RestController
@RequiredArgsConstructor
public class VerificationApiControllerHandler implements VerificationApi {

    private final VerificationCodeService verificationCodeService;

    private final CredentialRecoveryService credentialRecoveryService;

    @Override
    public ResponseEntity<VerificationCodeGenerationResponseDto> verificationCodeGenerationPostRequest(VerificationCodeGenerationByPassportRequestDto verificationCodeGenerationByPassportRequestDto) throws VerificationCodeNotGeneratedException {
        UserDto userDto = credentialRecoveryService.findClientInfoByPassportNumber(verificationCodeGenerationByPassportRequestDto.getPassportNumber());
        VerificationCodeGenerationResponseDto verificationCodeGenerationResponseDto = verificationCodeService.generateVerificationCode(userDto.getPhoneLogin(), PASSWORD_RECOVERY_REASON_CONSTANT)
                .orElseThrow(() -> new VerificationCodeNotGeneratedException (STATUS_CODE_BAD_REQUEST_VERIFICATION_CODE_NOT_GENERATED));
        return new ResponseEntity<>(verificationCodeGenerationResponseDto, verificationCodeGenerationResponseDto.getHttpStatus());
    }

    interface VerificationCodeApiConstant {

        String PASSWORD_RECOVERY_REASON_CONSTANT = "Password recovery";

    }

}
