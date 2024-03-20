package com.example.bank.service;

import com.example.bank.constant.Topic;
import com.example.bank.constant.TransactionType;
import com.example.bank.dto.CreditResponseMessage;
import com.example.bank.dto.DebitResponseMessage;
import com.example.bank.model.Account;
import com.example.bank.model.DeadCreditMessage;
import com.example.bank.model.DeadDebitMessage;
import com.example.bank.model.Transaction;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.DeadCreditMessageRepository;
import com.example.bank.repository.DeadDebitMessageRepository;
import com.example.bank.repository.TransactionRepository;
import com.example.bank.utils.DateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private KafkaTemplate<String, CreditResponseMessage> creditKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, DebitResponseMessage> debitKafkaTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DeadCreditMessageRepository deadCreditMessageRepository;

    @Autowired
    private DeadDebitMessageRepository deadDebitMessageRepository;

    @Autowired
    private DateService dateService;

    public Page<Transaction> getAllTransactionOfAnUser(Long userId, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("transaction_date").descending());
        return transactionRepository.getAllTransactionOfAnUser(userId, pageable);
    }

    @Transactional
    public void credit(String destinationAccountNumber, Long money, boolean transactionRecordCreatable) {
        Account account = accountService.findAccount(destinationAccountNumber);
        account.addMoney(money);
        accountRepository.save(account);
        if (transactionRecordCreatable) {
            Transaction transaction = Transaction.builder()
                    .transactionDate(dateService.getCurrentDate())
                    .transactionType(TransactionType.CREDIT)
                    .destinationAccountNumber(destinationAccountNumber)
                    .amount(money)
                    .build();
            transactionRepository.save(transaction);
        }
        CreditResponseMessage creditResponseMessage = new CreditResponseMessage(destinationAccountNumber, money);
        creditKafkaTemplate.send(Topic.CREDIT.getTopic(), creditResponseMessage)
                .exceptionally(throwable -> {
                    DeadCreditMessage deadCreditMessage = modelMapper.map(creditResponseMessage, DeadCreditMessage.class);
                    deadCreditMessageRepository.save(deadCreditMessage);
                    return null;
                });
    }

    @Transactional
    public void debit(String sourceAccountNumber, Long money, boolean transactionRecordCreatable) {
        Account account = accountService.findAccount(sourceAccountNumber);
        accountService.isBalanceSufficient(account, money);
        account.subtractMoney(money);
        accountRepository.save(account);
        if (transactionRecordCreatable) {
            Transaction transaction = Transaction.builder()
                    .transactionDate(dateService.getCurrentDate())
                    .transactionType(TransactionType.DEBIT)
                    .sourceAccountNumber(sourceAccountNumber)
                    .amount(money)
                    .build();
            transactionRepository.save(transaction);
        }
        DebitResponseMessage debitResponseMessage = new DebitResponseMessage(sourceAccountNumber, money);
        debitKafkaTemplate.send(Topic.DEBIT.getTopic(), debitResponseMessage)
                .exceptionally(throwable -> {
                    DeadDebitMessage deadDebitMessage = modelMapper.map(debitResponseMessage, DeadDebitMessage.class);
                    deadDebitMessageRepository.save(deadDebitMessage);
                    return null;
                });
    }

    @Transactional
    public void transfer(String sourceAccountNumber, String destinationAccountNumber, Long money) {
        this.debit(sourceAccountNumber, money, false);
        this.credit(destinationAccountNumber, money, false);
        Transaction transaction = Transaction.builder()
                .transactionDate(dateService.getCurrentDate())
                .transactionType(TransactionType.TRANSFER)
                .sourceAccountNumber(sourceAccountNumber)
                .destinationAccountNumber(destinationAccountNumber)
                .amount(money)
                .build();
        transactionRepository.save(transaction);
    }
}
