package dev.alexfossa204.starbank.authorization.service.impl;

import dev.alexfossa204.starbank.authorization.service.dto.UserDto;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationTopicMessage;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationResponseDto;
import dev.alexfossa204.starbank.authorization.repository.model.Passport;
import dev.alexfossa204.starbank.authorization.repository.model.UserContact;
import dev.alexfossa204.starbank.authorization.repository.UserContactRepository;
import dev.alexfossa204.starbank.authorization.service.MessageBrokerTopicProducerService;
import dev.alexfossa204.starbank.authorization.service.VerificationCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

import static dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant.*;

@RequiredArgsConstructor
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final UserContactRepository userContactRepository;

    private final MessageBrokerTopicProducerService messageBrokerTopicProducerService;

    @Override
    public Optional<VerificationCodeGenerationResponseDto> generateVerificationCode(String phoneNumber, String reason) {
        Optional<UserContact> contactOptional = userContactRepository.findByPhoneNumber(phoneNumber);
        Optional<VerificationCodeGenerationResponseDto> verificationCodeResponseDtoOptional = Optional.empty();
        boolean isClient;
        if(contactOptional.isEmpty()) {
            isClient = false;
            Date verificationCodeGenerationTimeStamp = new Date();
            verificationCodeResponseDtoOptional = Optional.of(VerificationCodeGenerationResponseDto.builder()
                    .timeStamp(verificationCodeGenerationTimeStamp)
                    .httpStatus(HttpStatus.OK)
                    .message(STATUS_CODE_OK_VERIFICATION_CODE_SENT_AS_MESSAGE_FOR_NON_CLIENT)
                    .phoneNumber(phoneNumber)
                    .isClient(isClient)
                    .build());
            UserDto userDto = UserDto.builder()
                    .phoneLogin(phoneNumber)
                    .isClient(isClient)
                    .build();
            VerificationCodeGenerationTopicMessage topicMessage = VerificationCodeGenerationTopicMessage.builder()
                    .publicationReason(reason)
                    .publicationTimeStamp(verificationCodeGenerationTimeStamp)
                    .user(userDto)
                    .build();
            messageBrokerTopicProducerService.publishVerificationCodeGenerationTopicEvent(topicMessage);
        }
        if(contactOptional.isPresent()) {
            isClient = true;
            Date verificationCodeGenerationTimeStamp = new Date();
            verificationCodeResponseDtoOptional = Optional.of(VerificationCodeGenerationResponseDto.builder()
                    .timeStamp(verificationCodeGenerationTimeStamp)
                    .httpStatus(HttpStatus.OK)
                    .message(STATUS_CODE_OK_VERIFICATION_CODE_SENT_AS_MESSAGE_FOR_CLIENT)
                    .phoneNumber(phoneNumber)
                    .isClient(isClient)
                    .build());
            Passport passport = contactOptional.get().getUser().getPassport();
            UserContact userContact = contactOptional.get();
            UserDto userDto = UserDto.builder()
                    .firstName(passport.getFirstname())
                    .lastName(passport.getLastname())
                    .passportLogin(passport.getPassportSerialNumber())
                    .phoneLogin(userContact.getPhoneNumber())
                    .isClient(isClient)
                    .build();
            VerificationCodeGenerationTopicMessage topicMessage = VerificationCodeGenerationTopicMessage.builder()
                    .publicationReason(reason)
                    .publicationTimeStamp(verificationCodeGenerationTimeStamp)
                    .user(userDto)
                    .build();
            messageBrokerTopicProducerService.publishVerificationCodeGenerationTopicEvent(topicMessage);
        }
        return verificationCodeResponseDtoOptional;
    }

}
