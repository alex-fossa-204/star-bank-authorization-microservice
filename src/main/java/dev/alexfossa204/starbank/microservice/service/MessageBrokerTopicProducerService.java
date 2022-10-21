package dev.alexfossa204.starbank.microservice.service;

import dev.alexfossa204.starbank.microservice.service.dto.VerificationCodeGenerationTopicMessage;
import dev.alexfossa204.starbank.microservice.service.dto.VerificationCodeSetAsUsedTopicMessage;

public interface MessageBrokerTopicProducerService {

    void publishVerificationCodeGenerationTopicEvent(VerificationCodeGenerationTopicMessage topicMessage);

    void publishVerificationCodeSetAsUsedTopicEvent(VerificationCodeSetAsUsedTopicMessage topicMessage);

}
