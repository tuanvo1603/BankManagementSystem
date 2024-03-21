//package com.example.bank.service;
//
//import com.example.bank.constant.AccountType;
//import com.example.bank.dto.CreditResponseMessage;
//import com.example.bank.model.Account;
//import com.example.bank.repository.AccountRepository;
//import com.example.bank.repository.TransactionRepository;
//import com.example.bank.utils.DateService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class TransactionServiceTest {
//
//    @Mock
//    private AccountService accountService;
//
//    @Mock
//    private AccountRepository accountRepository;
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private DateService dateService;
//
//    @Mock
//    private KafkaTemplate<String, CreditResponseMessage> creditKafkaTemplate;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Autowired
//    private AccountService testedService;
//
//    @Test
//    void credit() {
//        String destinationAccountNumber = "1234567890";
//        Long money = 10000L;
//        boolean transactionRecordCreatable = true;
//        Account mockAccount = new Account(1L, AccountType.SAVINGS, 10000L, "1234567890");
//        when(accountService.findAccount(destinationAccountNumber)).thenReturn(mockAccount);
//
//        when()
//    }
//}