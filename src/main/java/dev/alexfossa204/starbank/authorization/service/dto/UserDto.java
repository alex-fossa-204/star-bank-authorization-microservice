package dev.alexfossa204.starbank.authorization.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

import static dev.alexfossa204.starbank.authorization.service.dto.UserDto.UserDtoConstant.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
public class UserDto implements Serializable {

    @JsonProperty(FIRSTNAME_JSON_PROPERTY_NAME)
    private String firstName;

    @JsonProperty(LASTNAME_JSON_PROPERTY_NAME)
    private String lastName;

    @JsonProperty(PHONE_LOGIN_JSON_PROPERTY_NAME)
    private String phoneLogin;

    @JsonProperty(PASSPORT_LOGIN_JSON_PROPERTY_NAME)
    private String passportLogin;

    @JsonProperty(IS_CLIENT_JSON_PROPERTY_NAME)
    private Boolean isClient;

    interface UserDtoConstant {

        String FIRSTNAME_JSON_PROPERTY_NAME = "firstName";

        String LASTNAME_JSON_PROPERTY_NAME = "lastName";

        String PHONE_LOGIN_JSON_PROPERTY_NAME = "phoneLogin";

        String PASSPORT_LOGIN_JSON_PROPERTY_NAME = "passportLogin";

        String IS_CLIENT_JSON_PROPERTY_NAME = "isClient";

    }
}
