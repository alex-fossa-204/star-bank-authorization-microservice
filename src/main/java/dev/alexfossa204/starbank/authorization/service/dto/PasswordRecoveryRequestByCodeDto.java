package dev.alexfossa204.starbank.authorization.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static dev.alexfossa204.starbank.authorization.service.dto.PasswordRecoveryRequestByCodeDto.PasswordRecoveryRequestByCodeDtoConstant.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PasswordRecoveryRequestByCodeDto implements DtoRegExConstantPassportProperty, DtoRegExConstantPasswordProperty, DtoRegExConstantVerificationCodeProperty {

    @JsonProperty(PASSPORT_NUMBER_JSON_PROPERTY_NAME)
    @Pattern(regexp = PASSPORT_NUMBER_REGEX_VALIDATION_VALUE, message = PASSPORT_NUMBER_REGEX_VALIDATION_MESSAGE)
    private String passportNumber;

    @JsonProperty(NEW_PASSWORD_JSON_PROPERTY_NAME)
    @Pattern(regexp = PASSWORD_REGEX_VALIDATION_VALUE, message = PASSWORD_REGEX_VALIDATION_MESSAGE)
    private String newPassword;

    @JsonProperty(value = CONFIRM_PASSWORD_JSON_PROPERTY_NAME)
    @Pattern(regexp = PASSWORD_REGEX_VALIDATION_VALUE, message = PASSWORD_REGEX_VALIDATION_MESSAGE)
    private String confirmPassword;

    @JsonProperty(VERIFICATION_CODE_JSON_PROPERTY_NAME)
    @Pattern(regexp = VERIFICATION_CODE_REGEX_VALIDATION_VALUE, message = VERIFICATION_CODE_REGEX_VALIDATION_MESSAGE)
    private String verificationCode;

    interface PasswordRecoveryRequestByCodeDtoConstant {

        String PASSPORT_NUMBER_JSON_PROPERTY_NAME = "passportNumber";

        String NEW_PASSWORD_JSON_PROPERTY_NAME = "newPassword";

        String CONFIRM_PASSWORD_JSON_PROPERTY_NAME = "confirmPassword";

        String VERIFICATION_CODE_JSON_PROPERTY_NAME = "verificationCode";

    }

}
