package com.banking.onlinebanking.repository;

import com.banking.onlinebanking.repository.entity.TransactionDetails;
import com.banking.onlinebanking.repository.entity.TransactionTypeDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Integer> {

}
