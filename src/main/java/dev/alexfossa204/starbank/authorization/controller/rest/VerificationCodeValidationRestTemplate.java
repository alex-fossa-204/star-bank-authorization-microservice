package dev.alexfossa204.starbank.authorization.controller.rest;

import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeCheckRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeCheckResponseDto;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeExpiredException;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeNotFoundException;
import dev.alexfossa204.starbank.authorization.utils.json.CustomJsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


import static dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant.*;

@Component
@Qualifier("verificationCodeValidationRestTemplate")
@RequiredArgsConstructor
public class VerificationCodeValidationRestTemplate {

    private final RestTemplate restTemplate;

    private final CustomJsonParser<VerificationCodeCheckResponseDto> verificationCodeCheckResponseDtoCustomJsonParser;

    private final URI verificationCodeValidationPostRequestUri;

    public void sendVerificationCodeValidationPostRequest(String phoneNumber, String verificationCode) throws VerificationCodeExpiredException, VerificationCodeNotFoundException {
        VerificationCodeCheckRequestDto requestDto = VerificationCodeCheckRequestDto.builder()
                .phoneNumber(phoneNumber)
                .verificationCode(verificationCode)
                .build();
        try {
            restTemplate.postForEntity(verificationCodeValidationPostRequestUri, requestDto, VerificationCodeCheckResponseDto.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            VerificationCodeCheckResponseDto responseObject = verificationCodeCheckResponseDtoCustomJsonParser.parseJsonString(httpClientErrorException.getResponseBodyAsString());
            HttpStatus httpStatus = responseObject.getHttpStatus();
            String responseMessage = responseObject.getMessage();
            if(httpStatus.equals(HttpStatus.BAD_REQUEST) && responseMessage.equalsIgnoreCase(VERIFICATION_CODE_ALREADY_USED_OR_UNAVAILABLE)) {
                throw new VerificationCodeExpiredException(responseMessage);
            }
            if (httpStatus.equals(HttpStatus.UNAUTHORIZED) && responseMessage.equalsIgnoreCase(VERIFICATION_CODE_NOT_FOUND_MSG)) {
                throw new VerificationCodeNotFoundException(responseMessage);
            }
        }
    }

}
