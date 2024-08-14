package com.banking.onlinebanking.repository;

import com.banking.onlinebanking.repository.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails, String> {
    Optional<AccountDetails> findByAccountNumber(String accountNumber);
    void deleteAllByAccountStatusCurrentAccountStatus(String currentAccountStatus);
}
