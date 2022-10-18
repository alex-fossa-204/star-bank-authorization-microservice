package dev.alexfossa204.starbank.authorization.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static dev.alexfossa204.starbank.authorization.service.dto.RoleResponseDto.RoleResponseDtoConstant.PRIVILEGES_JSON_PROPERTY_NAME;
import static dev.alexfossa204.starbank.authorization.service.dto.RoleResponseDto.RoleResponseDtoConstant.ROLE_NAME_JSON_PROPERTY_NAME;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class RoleResponseDto extends HttpResponseDto {

    @JsonProperty(ROLE_NAME_JSON_PROPERTY_NAME)
    private String roleName;

    @JsonProperty(PRIVILEGES_JSON_PROPERTY_NAME)
    private List<String> privileges;

    interface RoleResponseDtoConstant {

        String ROLE_NAME_JSON_PROPERTY_NAME = "roleName";

        String PRIVILEGES_JSON_PROPERTY_NAME = "privileges";

    }

}
