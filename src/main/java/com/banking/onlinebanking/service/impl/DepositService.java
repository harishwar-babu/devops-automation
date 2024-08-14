package com.banking.onlinebanking.service.impl;
import com.banking.onlinebanking.converter.TransactionDetailsConverterForDto;
import com.banking.onlinebanking.dto.TransactionDetailsRequestDto;
import com.banking.onlinebanking.dto.TransactionDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNotActivatedException;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.repository.AccountDetailsRepository;
import com.banking.onlinebanking.repository.TransactionDetailsRepository;
import com.banking.onlinebanking.repository.TransactionTypeRepository;
import com.banking.onlinebanking.repository.entity.AccountDetails;
import com.banking.onlinebanking.repository.entity.TransactionDetails;
import com.banking.onlinebanking.service.TransactionService;
import com.banking.onlinebanking.util.AccountStatusUtil;
import com.banking.onlinebanking.util.ApplicationConstant;
import com.banking.onlinebanking.util.TransactionTypeUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class DepositService implements TransactionService {
    private final AccountDetailsRepository accountDetailsRepository;
    private final TransactionDetailsRepository transactionDetailsRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionDetailsConverterForDto transactionDetailsConverter;
    @Override
    public TransactionDetailsResponseDto makeTransaction(TransactionDetailsRequestDto transactionDetailsRequest) throws
            AccountNumberDoesNotExistsException, AccountNotActivatedException {
        AccountDetails accountDetails = accountDetailsRepository.findByAccountNumber(transactionDetailsRequest.getAccountNumber())
                .orElseThrow(()->new AccountNumberDoesNotExistsException(ApplicationConstant.ACCOUNT_DOES_NOT_EXISTS_MESSAGE));
        if(!accountDetails.getAccountStatus().getCurrentAccountStatusCode().equalsIgnoreCase(AccountStatusUtil.ACTIVE.getCurrentAccountStatusCode())){
            throw new AccountNotActivatedException(String.format(ApplicationConstant.ACCOUNT_NOT_ACTIVATED_MESSAGE,
                    accountDetails.getAccountNumber()));
        }
        accountDetails.setBankBalance(accountDetails.getBankBalance()+ transactionDetailsRequest.getAmount());
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setTransactionDate(LocalDateTime.now());
        transactionDetails.setDepositAmount(transactionDetailsRequest.getAmount());
        transactionDetails.setAccountNumber(transactionDetailsRequest.getAccountNumber());
        transactionDetails.setTransactionTypeDescription(transactionTypeRepository.getReferenceById(
                TransactionTypeUtil.DEPOSIT.getTransactionTypeCode()));
        accountDetailsRepository.save(accountDetails);
        transactionDetailsRepository.save(transactionDetails);
        return transactionDetailsConverter.convertToDto(transactionDetails);
    }
}
