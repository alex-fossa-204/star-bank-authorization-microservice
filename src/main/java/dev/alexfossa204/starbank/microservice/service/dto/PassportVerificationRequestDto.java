package dev.alexfossa204.starbank.microservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static dev.alexfossa204.starbank.microservice.service.dto.PassportVerificationRequestDto.PassportVerificationRequestDtoConstant.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PassportVerificationRequestDto implements DtoRegExConstantPassportProperty {

    @Pattern(regexp = PASSPORT_NUMBER_REGEX_VALIDATION_VALUE, message = PASSPORT_NUMBER_REGEX_VALIDATION_MESSAGE)
    @JsonProperty(PASSPORT_NUMBER_JSON_PROPERTY_NAME)
    private String passportNumber;

    interface PassportVerificationRequestDtoConstant {

        String PASSPORT_NUMBER_JSON_PROPERTY_NAME = "passportNumber";

    }

}
