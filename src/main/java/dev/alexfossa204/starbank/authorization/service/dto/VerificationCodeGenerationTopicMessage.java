package dev.alexfossa204.starbank.authorization.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import static dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationTopicMessage.VerificationCodeGenerationTopicMessageConstant.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Accessors(chain = true)
public class VerificationCodeGenerationTopicMessage implements Serializable {

    @JsonProperty(PUBLICATION_REASON_JSON_PROPERTY)
    private String publicationReason;

    @JsonProperty(PUBLICATION_TIMESTAMP_JSON_PROPERTY)
    private Date publicationTimeStamp;

    @JsonProperty(USER_JSON_PROPERTY)
    private UserDto user;

    interface VerificationCodeGenerationTopicMessageConstant {

        String PUBLICATION_REASON_JSON_PROPERTY = "reason";

        String PUBLICATION_TIMESTAMP_JSON_PROPERTY = "messagePublicationDate";

        String USER_JSON_PROPERTY = "user";

    }

}
