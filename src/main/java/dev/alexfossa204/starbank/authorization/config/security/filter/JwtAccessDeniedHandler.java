package dev.alexfossa204.starbank.authorization.config.security.filter;

import dev.alexfossa204.starbank.authorization.service.dto.HttpResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexfossa204.starbank.authorization.config.security.constant.JwtTokenConfigurationConstant;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.*;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        HttpResponseDto httpResponseDto = HttpResponseDto.builder()
                .timeStamp(new Date())
                .httpStatus(FORBIDDEN)
                .message(JwtTokenConfigurationConstant.ACCESS_DENIED_MSG)
                .build();
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(FORBIDDEN.value());
        OutputStream outputStream = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(outputStream, httpResponseDto);
        outputStream.flush();
    }
}
