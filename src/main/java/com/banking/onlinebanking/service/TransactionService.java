package com.banking.onlinebanking.service;

import com.banking.onlinebanking.dto.TransactionDetailsRequestDto;
import com.banking.onlinebanking.dto.TransactionDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNotActivatedException;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.exceptions.LessThanBankBalanceException;

public interface TransactionService {
    TransactionDetailsResponseDto makeTransaction(TransactionDetailsRequestDto transactionDetailsRequest) throws
            AccountNumberDoesNotExistsException, LessThanBankBalanceException, AccountNotActivatedException;
}
