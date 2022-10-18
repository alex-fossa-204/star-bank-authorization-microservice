package dev.alexfossa204.starbank.authorization.utils.json;

import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeCheckResponseDto;
import dev.alexfossa204.starbank.authorization.service.exception.VerificationCodeNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static dev.alexfossa204.starbank.authorization.service.constant.ServiceExceptionConstant.VERIFICATION_CODE_NOT_FOUND_MSG;

@AllArgsConstructor
@Component
@Qualifier("verificationCodeCheckResponseDtoCustomJsonParser")
public class VerificationCodeCheckResponseDtoCustomJsonParser implements CustomJsonParser<VerificationCodeCheckResponseDto> {

    private final ObjectMapper objectMapper;

    @Override
    public VerificationCodeCheckResponseDto parseJsonString(String jsonString) throws VerificationCodeNotFoundException {
        Optional<VerificationCodeCheckResponseDto> codeCheckResponseDtoOptional = Optional.empty();
        try {
            VerificationCodeCheckResponseDto dto = objectMapper.readValue(jsonString, VerificationCodeCheckResponseDto.class);
            codeCheckResponseDtoOptional = Optional.of(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        if(codeCheckResponseDtoOptional.isEmpty()) {
            throw new VerificationCodeNotFoundException(VERIFICATION_CODE_NOT_FOUND_MSG);
        }
        return codeCheckResponseDtoOptional.get();
    }
}
