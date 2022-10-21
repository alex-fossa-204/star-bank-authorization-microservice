package dev.alexfossa204.starbank.microservice.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static dev.alexfossa204.starbank.microservice.service.dto.HttpResponseDto.HttpResponseDtoConstant.*;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class HttpResponseDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = TIMESTAMP_REGEX_PATTERN_VALUE, timezone = TIMESTAMP_ZONE_VALUE)
    @JsonProperty(TIMESTAMP_JSON_PROPERTY_NAME)
    private Date timeStamp;

    @JsonProperty(HTTP_STATUS_JSON_PROPERTY_NAME)
    private HttpStatus httpStatus;

    @JsonProperty(MESSAGE_JSON_PROPERTY_NAME)
    private String message;

    interface HttpResponseDtoConstant {

        String TIMESTAMP_JSON_PROPERTY_NAME = "timeStamp";

        String TIMESTAMP_REGEX_PATTERN_VALUE = "dd-MM-yyyy HH:mm:ss";

        String TIMESTAMP_ZONE_VALUE = "Europe/Minsk";

        String HTTP_STATUS_JSON_PROPERTY_NAME = "httpStatus";

        String MESSAGE_JSON_PROPERTY_NAME = "message";

    }

}
