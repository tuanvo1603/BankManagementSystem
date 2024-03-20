package com.example.bank.schedule;

import com.example.bank.constant.Topic;
import com.example.bank.dto.CreditResponseMessage;
import com.example.bank.model.DeadCreditMessage;
import com.example.bank.repository.DeadCreditMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DeadCreditMessageSchedule implements CompletableMessage {

    @Autowired
    private DeadCreditMessageRepository deadCreditMessageRepository;

    @Autowired
    private KafkaTemplate<String, CreditResponseMessage> creditKafkaTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Scheduled(fixedDelay = 10000)
    @Override
    public void replayMessage() {
        log.info("A poll of dead credit message.");
        List<DeadCreditMessage> deadCreditMessages = deadCreditMessageRepository.findAll();
        deadCreditMessages.forEach(deadCreditMessage -> {
            CreditResponseMessage creditResponseMessage = modelMapper.map(deadCreditMessage, CreditResponseMessage.class);
            creditKafkaTemplate.send(Topic.CREDIT.getTopic(), creditResponseMessage).thenRun(() -> {
                deadCreditMessageRepository.deleteById(deadCreditMessage.getId());
            });
        });
    }
}
