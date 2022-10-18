package dev.alexfossa204.starbank.authorization.service.impl;

import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeGenerationTopicMessage;
import dev.alexfossa204.starbank.authorization.service.dto.VerificationCodeSetAsUsedTopicMessage;
import dev.alexfossa204.starbank.authorization.service.MessageBrokerTopicProducerService;
import dev.alexfossa204.starbank.authorization.config.kafka.KafkaConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaTopicProducerService implements MessageBrokerTopicProducerService {

    private final KafkaTemplate<String, VerificationCodeGenerationTopicMessage> verificationCodeGenerationTopicMessageKafkaTemplate;

    private final KafkaTemplate<String, VerificationCodeSetAsUsedTopicMessage> verificationCodeSetAsUsedKafkaTemplate;

    @Override
    @Async
    public void publishVerificationCodeGenerationTopicEvent(VerificationCodeGenerationTopicMessage topicMessage) {
        verificationCodeGenerationTopicMessageKafkaTemplate.send(KafkaConstant.GENERATE_VERIFICATION_CODE_TOPIC, topicMessage);
    }

    @Override
    @Async
    public void publishVerificationCodeSetAsUsedTopicEvent(VerificationCodeSetAsUsedTopicMessage topicMessage) {
        verificationCodeSetAsUsedKafkaTemplate.send(KafkaConstant.SET_VERIFICATION_CODE_AS_USED_TOPIC, topicMessage);
    }


}
