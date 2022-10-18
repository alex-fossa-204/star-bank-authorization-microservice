package dev.alexfossa204.starbank.authorization.service;

import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationTopicMessage;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeSetAsUsedTopicMessage;

public interface MessageBrokerTopicProducerService {

    void publishVerificationCodeGenerationTopicEvent(VerificationCodeGenerationTopicMessage topicMessage);

    void publishVerificationCodeSetAsUsedTopicEvent(VerificationCodeSetAsUsedTopicMessage topicMessage);

}
