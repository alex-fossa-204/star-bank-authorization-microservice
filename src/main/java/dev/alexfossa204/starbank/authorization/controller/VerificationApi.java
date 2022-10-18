package dev.alexfossa204.starbank.authorization.controller;

import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationByPassportRequestDto;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationResponseDto;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeNotGeneratedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Tag(name = "Verification Code management API", description = "This Component is responsible for Verification code generation and code manipulations")
@RequestMapping(value = "/star-bank/recovery-management")
public interface VerificationApi {

    @Operation(
            summary = "[Password Recovery 2-nd step] Generate 6 digit verification code",
            method = "sendRequestForVerificationCodeGeneration",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "__[Verification code generation request body]:__\n\n" +
                            " __(passportNumber)__ - represents passport number, which is used as credential. If client registered, this request will detect phone number for verification code delivery by it self."),
            description = "Generates an event on Kafka Cluster. Event reason: code generation. Verification Code Microserivce listens for this event")
    @RequestMapping(value = "user/verification-code/new-code", method = RequestMethod.POST)
    ResponseEntity<VerificationCodeGenerationResponseDto> verificationCodeGenerationPostRequest(@RequestBody
                                                                                                @Parameter(name = "User Phone Number", required = true)
                                                                                                @Valid VerificationCodeGenerationByPassportRequestDto verificationCodeGenerationByPassportRequestDto) throws VerificationCodeNotGeneratedException;

}
