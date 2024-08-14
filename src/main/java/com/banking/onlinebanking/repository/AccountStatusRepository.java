package com.banking.onlinebanking.repository;

import com.banking.onlinebanking.repository.entity.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStatusRepository extends JpaRepository<AccountStatus, String> {

}
