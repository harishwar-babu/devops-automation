package com.banking.onlinebanking.repository;

import com.banking.onlinebanking.repository.entity.TransactionTypeDescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository extends JpaRepository<TransactionTypeDescription, String> {

}
