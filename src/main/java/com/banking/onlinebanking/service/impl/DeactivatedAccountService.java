package com.banking.onlinebanking.service.impl;

import com.banking.onlinebanking.repository.AccountDetailsRepository;
import com.banking.onlinebanking.util.AccountStatusUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeactivatedAccountService {
    private final AccountDetailsRepository accountDetailsRepository;
    public void removeDeactivatedAccounts(){
        accountDetailsRepository.deleteAllByAccountStatusCurrentAccountStatus(AccountStatusUtil.DEACTIVATED.name());
    }
}
