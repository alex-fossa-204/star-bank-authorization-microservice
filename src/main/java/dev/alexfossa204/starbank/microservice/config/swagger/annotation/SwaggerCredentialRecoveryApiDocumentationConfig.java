package dev.alexfossa204.starbank.microservice.config.swagger.annotation;

import io.swagger.v3.oas.annotations.media.Content;
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
        @ApiResponse(responseCode = "400", description = STATUS_CODE_BAD_REQUEST, content = @Content),
        @ApiResponse(responseCode = "401", description = STATUS_CODE_UNAUTHORIZED, content = @Content),
        @ApiResponse(responseCode = "500", description = STATUS_CODE_INTERNAL_SERVER_ERROR, content = @Content)
})
public @interface SwaggerCredentialRecoveryApiDocumentationConfig {

}
