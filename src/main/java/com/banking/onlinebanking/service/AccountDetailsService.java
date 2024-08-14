package com.banking.onlinebanking.service;
import com.banking.onlinebanking.dto.AccountDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;

import java.util.List;
public interface AccountDetailsService {
    String activateAccount(String accountNumber) throws AccountNumberDoesNotExistsException;
    List<AccountDetailsResponseDto> viewBankAccounts();
    AccountDetailsResponseDto viewAccountDetail(String accountNumber) throws AccountNumberDoesNotExistsException;
    String deActivateAccount(String accountNumber) throws AccountNumberDoesNotExistsException;
}
