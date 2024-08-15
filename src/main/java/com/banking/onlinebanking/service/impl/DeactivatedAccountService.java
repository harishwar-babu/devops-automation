package com.banking.onlinebanking.service.impl;

import com.banking.onlinebanking.repository.AccountDetailsRepository;
import com.banking.onlinebanking.util.AccountStatusUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class DeactivatedAccountService {
    private final AccountDetailsRepository accountDetailsRepository;
    public void removeDeactivatedAccounts(){
        accountDetailsRepository.deleteAllByAccountStatusCurrentAccountStatus(AccountStatusUtil.DEACTIVATED.name());
        log.info("accounts with status : {} has been removed",AccountStatusUtil.DEACTIVATED.name());
    }
}
