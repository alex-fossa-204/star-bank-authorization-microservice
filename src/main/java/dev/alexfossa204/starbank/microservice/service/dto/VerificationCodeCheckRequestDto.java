package dev.alexfossa204.starbank.microservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.Pattern;

import static dev.alexfossa204.starbank.microservice.service.dto.VerificationCodeCheckRequestDto.VerificationCodeCheckRequestDtoConstant.*;

@Builder
@Data
public class VerificationCodeCheckRequestDto implements DtoRegExConstantVerificationCodeProperty, DtoRegExConstantPhoneNumberProperty {

    @JsonProperty(VERIFICATION_CODE_JSON_PROPERTY_NAME)
    @Pattern(regexp = VERIFICATION_CODE_REGEX_VALIDATION_VALUE, message = VERIFICATION_CODE_REGEX_VALIDATION_MESSAGE)
    private String verificationCode;

    @JsonProperty(PHONE_NUMBER_JSON_PROPERTY_NAME)
    @Pattern(regexp = PHONE_NUMBER_REGEX_VALIDATION_VALUE, message = PHONE_NUMBER_REGEX_VALIDATION_MESSAGE)
    private String phoneNumber;

    interface VerificationCodeCheckRequestDtoConstant {

        String VERIFICATION_CODE_JSON_PROPERTY_NAME = "verificationCode";

        String PHONE_NUMBER_JSON_PROPERTY_NAME = "phoneNumber";

    }

}
