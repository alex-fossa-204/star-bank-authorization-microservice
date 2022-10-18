package dev.alexfossa204.starbank.authorization.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationByPassportRequestDto.VerificationCodeGenerationByPassportRequestDtoConstant.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VerificationCodeGenerationByPassportRequestDto implements DtoRegExConstantPassportProperty {

    @JsonProperty(PASSPORT_NUMBER_JSON_PROPERTY_NAME)
    @Pattern(regexp = PASSPORT_NUMBER_REGEX_VALIDATION_VALUE, message = PASSPORT_NUMBER_REGEX_VALIDATION_MESSAGE)
    private String passportNumber;

    interface VerificationCodeGenerationByPassportRequestDtoConstant {

        String PASSPORT_NUMBER_JSON_PROPERTY_NAME = "passportNumber";

    }

}
