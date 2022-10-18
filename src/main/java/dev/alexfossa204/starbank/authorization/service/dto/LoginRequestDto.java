package dev.alexfossa204.starbank.authorization.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static dev.alexfossa204.starbank.authorization.service.dto.LoginRequestDto.LoginRequestDtoConstant.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LoginRequestDto implements DtoRegExConstantLoginProperty, DtoRegExConstantPasswordProperty {

    @Pattern(regexp = LOGIN_VALIDATION_REGEX_VALUE, message = LOGIN_VALIDATION_REGEX_MESSAGE)
    @JsonProperty(LOGIN_JSON_PROPERTY_NAME)
    private String login;

    @NotBlank(message = PASSWORD_NOT_BLANK_VALIDATION_MESSAGE)
    @JsonProperty(PASSWORD_JSON_PROPERTY_NAME)
    private String password;

    interface LoginRequestDtoConstant {

        String LOGIN_JSON_PROPERTY_NAME = "login";

        String PASSWORD_JSON_PROPERTY_NAME = "password";

    }

}
