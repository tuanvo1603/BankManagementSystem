package com.example.manageUser.kafka;

import com.example.manageUser.enitity.User;
import com.example.manageUser.enitity.response.UserActivityLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaUserPublisher {
    @Autowired
    private KafkaTemplate<String,Object> template;



    public void sendLogUserToTopic(UserActivityLog userActivityLog) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("user-auth-info", userActivityLog).completable();
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + userActivityLog.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            userActivityLog.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }
}
