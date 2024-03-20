package com.example.bank.schedule;

import com.example.bank.constant.Topic;
import com.example.bank.dto.DebitResponseMessage;
import com.example.bank.model.DeadDebitMessage;
import com.example.bank.repository.DeadDebitMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class DeadDebitMessageSchedule implements CompletableMessage {

    @Autowired
    private DeadDebitMessageRepository deadDebitMessageRepository;

    @Autowired
    private KafkaTemplate<String, DebitResponseMessage> debitKafkaTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Scheduled(fixedDelay = 10000)
    @Override
    public void replayMessage() {
        log.info("A poll of dead debit message");
        List<DeadDebitMessage> deadDebitMessages = deadDebitMessageRepository.findAll();
        deadDebitMessages.forEach(deadDebitMessage -> {
            DebitResponseMessage debitResponseMessage = modelMapper.map(deadDebitMessage, DebitResponseMessage.class);
            debitKafkaTemplate.send(Topic.DEBIT.getTopic(), debitResponseMessage).thenRun(() -> {
                deadDebitMessageRepository.deleteById(deadDebitMessage.getId());
            });
        });
    }
}
