package dev.alexfossa204.starbank.authorization.service;

import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationResponseDto;

import java.util.Optional;

public interface VerificationCodeService {

    Optional<VerificationCodeGenerationResponseDto> generateVerificationCode(String phoneNumber, String reason);

}
