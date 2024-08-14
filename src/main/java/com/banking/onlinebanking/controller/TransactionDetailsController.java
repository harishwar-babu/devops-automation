package com.banking.onlinebanking.controller;
import com.banking.onlinebanking.dto.ApiResponse;
import com.banking.onlinebanking.dto.TransactionDetailsRequestDto;
import com.banking.onlinebanking.dto.TransactionDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNotActivatedException;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.exceptions.LessThanBankBalanceException;
import com.banking.onlinebanking.service.TransactionService;
import com.banking.onlinebanking.util.ApplicationConstant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionDetailsController {
    private final TransactionService depositTransactionService;
    private final TransactionService withDrawTransactionService;
    public TransactionDetailsController(@Qualifier("depositService") TransactionService depositTransactionService,
                                        @Qualifier("withDrawService") TransactionService withDrawTransactionService){
        this.depositTransactionService = depositTransactionService;
        this.withDrawTransactionService = withDrawTransactionService;
    }
    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<TransactionDetailsResponseDto>> makeDepositTransaction(@Valid @RequestBody TransactionDetailsRequestDto transactionDetailsRequest)
            throws LessThanBankBalanceException, AccountNumberDoesNotExistsException, AccountNotActivatedException {
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE,depositTransactionService
                .makeTransaction(transactionDetailsRequest),null));
    }
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<TransactionDetailsResponseDto>> makeWithDrawTransaction(@Valid @RequestBody TransactionDetailsRequestDto transactionDetailsRequest)
            throws LessThanBankBalanceException, AccountNumberDoesNotExistsException, AccountNotActivatedException {
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE,withDrawTransactionService
                .makeTransaction(transactionDetailsRequest),null));
    }
}
