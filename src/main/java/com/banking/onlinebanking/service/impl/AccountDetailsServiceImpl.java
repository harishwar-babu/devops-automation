package com.banking.onlinebanking.service.impl;
import com.banking.onlinebanking.converter.AccountDetailsConverterForDto;
import com.banking.onlinebanking.dto.AccountDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.repository.AccountDetailsRepository;
import com.banking.onlinebanking.repository.AccountStatusRepository;
import com.banking.onlinebanking.repository.entity.AccountDetails;
import com.banking.onlinebanking.service.AccountDetailsService;
import com.banking.onlinebanking.util.AccountStatusUtil;
import com.banking.onlinebanking.util.ApplicationConstant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {
    private final AccountDetailsRepository accountDetailsRepository;
    private final AccountStatusRepository accountStatusRepository;
    private final AccountDetailsConverterForDto accountDetailsConverter;
    @Override
    public String activateAccount(String accountNumber) throws AccountNumberDoesNotExistsException {
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new AccountNumberDoesNotExistsException(ApplicationConstant.ACCOUNT_DOES_NOT_EXISTS_MESSAGE));
        accountDetails.setAccountStatus(accountStatusRepository.getReferenceById(AccountStatusUtil.ACTIVE
                .getCurrentAccountStatusCode()));
        accountDetailsRepository.save(accountDetails);
        return AccountStatusUtil.ACTIVE.name();
    }

    @Override
    public List<AccountDetailsResponseDto> viewBankAccounts() {
        return accountDetailsRepository.findAll().stream().map(accountDetailsConverter::convertToDto).toList();
    }

    @Override
    public AccountDetailsResponseDto viewAccountDetail(String accountNumber) throws AccountNumberDoesNotExistsException {
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new AccountNumberDoesNotExistsException(ApplicationConstant.ACCOUNT_DOES_NOT_EXISTS_MESSAGE));
        return accountDetailsConverter.convertToDto(accountDetails);
    }

    @Override
    public String deActivateAccount(String accountNumber) throws AccountNumberDoesNotExistsException {
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(accountNumber).
                orElseThrow(()->new AccountNumberDoesNotExistsException(ApplicationConstant.ACCOUNT_DOES_NOT_EXISTS_MESSAGE));
        accountDetails.setAccountStatus(accountStatusRepository.getReferenceById(AccountStatusUtil.DEACTIVATED
                .getCurrentAccountStatusCode()));
        accountDetailsRepository.save(accountDetails);
        return AccountStatusUtil.DEACTIVATED.name();
    }
}
