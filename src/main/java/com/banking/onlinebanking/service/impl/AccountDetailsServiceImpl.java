package com.banking.onlinebanking.service.impl;
import com.banking.onlinebanking.converter.AccountDetailsConverterForDto;
import com.banking.onlinebanking.dto.AccountDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.repository.AccountDetailsRepository;
import com.banking.onlinebanking.repository.AccountStatusRepository;
import com.banking.onlinebanking.repository.UserDetailsRepository;
import com.banking.onlinebanking.repository.entity.AccountDetails;
import com.banking.onlinebanking.repository.entity.UserDetails;
import com.banking.onlinebanking.service.AccountDetailsService;
import com.banking.onlinebanking.util.AccountStatusUtil;
import com.banking.onlinebanking.util.ApplicationConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Slf4j
public class AccountDetailsServiceImpl implements AccountDetailsService {
    private final AccountDetailsRepository accountDetailsRepository;
    private final AccountStatusRepository accountStatusRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final AccountDetailsConverterForDto accountDetailsConverter;
    @Override
    public String activateAccount(String accountNumber) throws AccountNumberDoesNotExistsException {
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new AccountNumberDoesNotExistsException(ApplicationConstant.ACCOUNT_DOES_NOT_EXISTS_MESSAGE));
        accountDetails.setAccountStatus(accountStatusRepository.getReferenceById(AccountStatusUtil.ACTIVE
                .getCurrentAccountStatusCode()));
        UserDetails userDetails = userDetailsRepository.findByCustomerId(accountDetails.getCustomerId()).orElseThrow(()
                ->new NoSuchElementException(ApplicationConstant.USER_RECORD_DOES_NOT_EXISTS_MESSAGE));
        userDetails.setAccountStatus(accountStatusRepository.getReferenceById(AccountStatusUtil
                .ACTIVE.getCurrentAccountStatusCode()));
        userDetailsRepository.save(userDetails);
        accountDetailsRepository.save(accountDetails);
        log.info("Account number : {} has been activated",accountNumber);
        return AccountStatusUtil.ACTIVE.name();
    }

    @Override
    public List<AccountDetailsResponseDto> viewBankAccounts() {
        log.info("Details of all bank accounts has been generated");
        return accountDetailsRepository.findAll().stream().map(accountDetailsConverter::convertToDto).toList();
    }

    @Override
    public AccountDetailsResponseDto viewAccountDetail(String accountNumber) throws AccountNumberDoesNotExistsException {
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new AccountNumberDoesNotExistsException(ApplicationConstant.ACCOUNT_DOES_NOT_EXISTS_MESSAGE));
        log.info("Details for account number: {} has been generated",accountNumber);
        return accountDetailsConverter.convertToDto(accountDetails);
    }

    @Override
    public String deActivateAccount(String accountNumber) throws AccountNumberDoesNotExistsException {
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new AccountNumberDoesNotExistsException(ApplicationConstant.ACCOUNT_DOES_NOT_EXISTS_MESSAGE));
        accountDetails.setAccountStatus(accountStatusRepository.getReferenceById(AccountStatusUtil.DEACTIVATED
                .getCurrentAccountStatusCode()));
        UserDetails userDetails = userDetailsRepository.findByCustomerId(accountDetails.getCustomerId()).orElseThrow(()
                ->new NoSuchElementException(ApplicationConstant.USER_RECORD_DOES_NOT_EXISTS_MESSAGE));
        userDetails.setAccountStatus(accountStatusRepository.getReferenceById(AccountStatusUtil
                .DEACTIVATED.getCurrentAccountStatusCode()));
        accountDetailsRepository.save(accountDetails);
        userDetailsRepository.save(userDetails);
        log.info("Account number : {} has been deactivated",accountNumber);
        return AccountStatusUtil.DEACTIVATED.name();
    }
}
