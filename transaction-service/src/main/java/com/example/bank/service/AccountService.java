package com.example.bank.service;

import com.example.bank.constant.Topic;
import com.example.bank.dto.CreditResponseMessage;
import com.example.bank.dto.DebitResponseMessage;
import com.example.bank.exception.AppException;
import com.example.bank.exception.ErrorCode;
import com.example.bank.model.Account;
import com.example.bank.model.DeadCreditMessage;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.DeadCreditMessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KafkaTemplate<String, CreditResponseMessage> creditKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, DebitResponseMessage> debitKafkaTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DeadCreditMessageRepository deadCreditMessageRepository;

    @Transactional
    public void credit(String destinationAccountNumber, Float money) {
        Account account = accountRepository.findByAccountNumber(destinationAccountNumber);
        if (destinationAccountNumber == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        account.addMoney(money);
        Account changedAccount = accountRepository.save(account);
        CreditResponseMessage creditResponseMessage = new CreditResponseMessage(changedAccount.getAccountNumber(), money);
        creditKafkaTemplate.send(Topic.CREDIT.getTopic(), creditResponseMessage).exceptionally(throwable -> {
            DeadCreditMessage deadCreditMessage = modelMapper.map(creditResponseMessage, DeadCreditMessage.class);
            deadCreditMessageRepository.save(deadCreditMessage);
            return null;
        });
    }

    private void isBalanceSufficient(Account account, Float money) {
        if (account.getBalance() < money) throw new AppException(ErrorCode.NOT_ENOUGH_MONEY_IN_ACCOUNT);
    }

    @Transactional
    public void debit(String sourceAccountNumber, Float money) {
        Account account = accountRepository.findByAccountNumber(sourceAccountNumber);
        if (sourceAccountNumber == null) {
            throw new AppException(ErrorCode.ACCOUNT_NOT_FOUND);
        }
        isBalanceSufficient(account, money);
        account.subtractMoney(money);
        Account changedAccount = accountRepository.save(account);
        DebitResponseMessage debitResponseMessage = new DebitResponseMessage(changedAccount.getAccountNumber(), money);
        debitKafkaTemplate.send(Topic.DEBIT.getTopic(), debitResponseMessage);
    }

}
