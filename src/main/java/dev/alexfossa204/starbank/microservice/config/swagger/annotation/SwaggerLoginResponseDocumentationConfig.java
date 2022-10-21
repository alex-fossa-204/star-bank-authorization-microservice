package dev.alexfossa204.starbank.microservice.config.swagger.annotation;


import dev.alexfossa204.starbank.microservice.service.dto.LoginResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static dev.alexfossa204.starbank.microservice.service.constant.ServiceExceptionConstant.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = STATUS_CODE_SUCCESS)
})
@Schema(implementation = LoginResponseDto.class)
public @interface SwaggerLoginResponseDocumentationConfig {
}
