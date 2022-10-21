package dev.alexfossa204.starbank.microservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static dev.alexfossa204.starbank.microservice.service.dto.VerificationCodeGenerationResponseDto.VerificationCodeGenerationResponseDtoConstant.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class VerificationCodeGenerationResponseDto extends HttpResponseDto {

    @JsonProperty(PHONE_NUMBER_JSON_PROPERTY_NAME)
    private String phoneNumber;

    @JsonProperty(IS_CLIENT_JSON_PROPERTY_NAME)
    private boolean isClient;

    interface VerificationCodeGenerationResponseDtoConstant {

        String PHONE_NUMBER_JSON_PROPERTY_NAME = "phoneNumber";

        String IS_CLIENT_JSON_PROPERTY_NAME = "isClient";

    }

}
