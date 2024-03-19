package com.example.bank.repository;

import com.example.bank.model.DeadDebitMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeadDebitMessageRepository extends JpaRepository<DeadDebitMessage, Long> {
}
