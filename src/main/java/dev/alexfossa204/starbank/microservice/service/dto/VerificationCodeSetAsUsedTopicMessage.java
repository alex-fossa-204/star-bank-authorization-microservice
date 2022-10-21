package dev.alexfossa204.starbank.microservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import static dev.alexfossa204.starbank.microservice.service.dto.VerificationCodeSetAsUsedTopicMessage.VerificationCodeSetAsUsedTopicMessageConstant.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class VerificationCodeSetAsUsedTopicMessage implements Serializable {

    @JsonProperty(PUBLICATION_REASON_JSON_PROPERTY_NAME)
    private String publicationReason;

    @JsonProperty(PUBLICATION_TIMESTAMP_JSON_PROPERTY_NAME)
    private Date publicationTimeStamp;

    @JsonProperty(PHONE_NUMBER_JSON_PROPERTY_NAME)
    private String phoneNumber;

    @JsonProperty(VERIFICATION_CODE_JSON_PROPERTY_NAME)
    private String verificationCode;

    interface VerificationCodeSetAsUsedTopicMessageConstant {

        String PUBLICATION_REASON_JSON_PROPERTY_NAME = "reason";

        String PUBLICATION_TIMESTAMP_JSON_PROPERTY_NAME = "messagePublicationDate";

        String PHONE_NUMBER_JSON_PROPERTY_NAME = "phoneNumber";

        String VERIFICATION_CODE_JSON_PROPERTY_NAME = "verificationCode";

    }

}
