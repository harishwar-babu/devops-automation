package com.banking.onlinebanking.service;
import com.banking.onlinebanking.dto.TransactionDetailsRequestDto;
import com.banking.onlinebanking.dto.TransactionDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNotActivatedException;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.exceptions.LessThanBankBalanceException;

public interface WithDrawService extends TransactionProcessor{
    @Override
    TransactionDetailsResponseDto makeTransaction(TransactionDetailsRequestDto transactionDetailsRequest) throws
            AccountNumberDoesNotExistsException, AccountNotActivatedException, LessThanBankBalanceException;
}
