package com.eva.tradingApp.service;

import com.eva.tradingApp.domain.entity.Transaction;
import com.eva.tradingApp.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

}
