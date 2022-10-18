package dev.alexfossa204.starbank.authorization.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

import static dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeCheckResponseDto.VerificationCodeCheckResponseDtoConstant.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Accessors(chain = true)
public class VerificationCodeCheckResponseDto extends HttpResponseDto implements Serializable {

    @JsonProperty(IS_EXPIRED_JSON_PROPERTY_NAME)
    private boolean isExpired;

    @JsonProperty(IS_USER_JSON_PROPERTY_NAME)
    private boolean isUsed;

    interface VerificationCodeCheckResponseDtoConstant {

        String IS_EXPIRED_JSON_PROPERTY_NAME = "isExpired";

        String IS_USER_JSON_PROPERTY_NAME = "isUsed";

    }

}
