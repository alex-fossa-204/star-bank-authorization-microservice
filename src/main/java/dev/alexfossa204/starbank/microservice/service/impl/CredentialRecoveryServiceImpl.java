package dev.alexfossa204.starbank.microservice.service.impl;

import dev.alexfossa204.starbank.microservice.mapper.impl.CredentialToUserDtoMapper;
import dev.alexfossa204.starbank.microservice.repository.model.Credential;
import dev.alexfossa204.starbank.microservice.repository.CredentialRepository;
import dev.alexfossa204.starbank.microservice.service.CredentialRecoveryService;
import dev.alexfossa204.starbank.microservice.service.constant.CredentialVerificationServiceConstant;
import dev.alexfossa204.starbank.microservice.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

import static dev.alexfossa204.starbank.microservice.service.constant.ServiceExceptionConstant.*;

@Service
@RequiredArgsConstructor
public class CredentialRecoveryServiceImpl implements CredentialRecoveryService {

    private final CredentialRepository credentialRepository;

    private final PasswordEncoder passwordEncoder;

    private final CredentialToUserDtoMapper credentialToUserDtoMapper;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public PassportVerificationResponseDto verifyUserPassport(PassportVerificationRequestDto passportVerificationRequestDto) {
        Optional<PassportVerificationResponseDto> passportVerificationResponseDto = Optional.empty();
        Optional<Credential> credentialOptional = credentialRepository.findCredentialByPassportLogin(passportVerificationRequestDto.getPassportNumber());
        if (credentialOptional.isEmpty()) {
            passportVerificationResponseDto = Optional.of(PassportVerificationResponseDto.builder()
                    .timeStamp(new Date())
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(CredentialVerificationServiceConstant.CREDENTIALS_WITH_SUCH_PASSPORT_NOT_FOUND)
                    .build());
        }
        if (credentialOptional.isPresent()) {
            passportVerificationResponseDto = Optional.of(PassportVerificationResponseDto.builder()
                    .timeStamp(new Date())
                    .httpStatus(HttpStatus.OK)
                    .message(CredentialVerificationServiceConstant.CREDENTIALS_PRESENT_OK)
                    .build());
        }
        return passportVerificationResponseDto.get();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.REPEATABLE_READ)
    @Override
    public HttpResponseDto resetCurrentUserPasswordByVerificationCode(PasswordRecoveryRequestByCodeDto credentialRecoveryDto) {
        Credential credential = credentialRepository.findCredentialByPassportLogin(credentialRecoveryDto.getPassportNumber())
                .orElseThrow(() -> new UsernameNotFoundException(CREDENTIAL_NOT_FOUND_MSG));
        String encodedPassword = passwordEncoder.encode(credentialRecoveryDto.getNewPassword());
        credential.setPassword(encodedPassword);
        credential.setIsNonLocked(true);
        credentialRepository.save(credential);
        return HttpResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(HttpStatus.OK)
                .message(STATUS_CODE_OK_NEW_PASSWORD_SAVED_BY_VERIFICATION_CODE)
                .build();
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    @Override
    public UserDto findClientInfoByPassportNumber(String passportNumber) {
        Credential credential = credentialRepository.findCredentialByPassportLogin(passportNumber)
                .orElseThrow(() -> new UsernameNotFoundException(CREDENTIAL_NOT_FOUND_MSG));
        UserDto userDto = credentialToUserDtoMapper.mapEntityToDto(credential);
        userDto.setIsClient(true);
        return userDto;
    }

}
