//package com.example.bank;
//
//import com.example.bank.api.TransferApi;
//import com.example.bank.request.TransferRequest;
//import com.example.bank.response.TransferResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.concurrent.ExecutionException;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//
//@SpringBootTest
//@Transactional
//@Rollback(value = false)
//public class TestExchangeMoney {
//
//    @Autowired
//    private TransferApi transferApi;
//
//    @Test
//    public void exchangeMoney() throws ExecutionException, InterruptedException {
//        TransferRequest transferRequest = new TransferRequest("232","2323",999f);
//        TransferResponse transferResponse = transferApi.execute(transferRequest);
//
//        assertNotNull(transferResponse);
//    }
//}
