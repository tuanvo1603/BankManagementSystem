package com.example.bank.service;

import com.example.bank.model.Transaction;
import com.example.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Page<Transaction> getAllTransactionOfAnUser(Long userId, Integer pageNumber, Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("transaction_date").descending());
        return transactionRepository.getAllTransactionOfAnUser(userId,pageable);
    };
}
