package com.example.bank.schedule;

import com.example.bank.constant.Topic;
import com.example.bank.dto.CreditResponseMessage;
import com.example.bank.model.DeadCreditMessage;
import com.example.bank.repository.DeadCreditMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeadTransactionSchedule {

    @Autowired
    private DeadCreditMessageRepository deadCreditMessageRepository;

    @Autowired
    private KafkaTemplate<String, CreditResponseMessage> creditKafkaTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Scheduled(fixedDelay = 10000)
    public void replayTransaction() {
        List<DeadCreditMessage> deadCreditMessages = deadCreditMessageRepository.findAll();
        deadCreditMessages.forEach(deadCreditMessage -> {
            CreditResponseMessage creditResponseMessage = modelMapper.map(deadCreditMessage, CreditResponseMessage.class);
            creditKafkaTemplate.send(Topic.CREDIT.getTopic(), creditResponseMessage).thenRun(() -> {
                deadCreditMessageRepository.deleteById(deadCreditMessage.getId());
            });
        });
    }
}
