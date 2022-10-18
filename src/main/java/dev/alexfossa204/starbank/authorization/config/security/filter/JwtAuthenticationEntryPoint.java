package dev.alexfossa204.starbank.authorization.config.security.filter;

import dev.alexfossa204.starbank.authorization.service.dto.HttpResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexfossa204.starbank.authorization.config.security.constant.JwtTokenConfigurationConstant;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException {
        HttpResponseDto httpResponseDto = HttpResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(UNAUTHORIZED)
                .message(JwtTokenConfigurationConstant.FORBIDDEN_MSG)
                .build();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(UNAUTHORIZED.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(outputStream, httpResponseDto);
        outputStream.flush();
    }
}
