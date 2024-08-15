package com.banking.onlinebanking.controller;
import com.banking.onlinebanking.dto.ApiResponse;
import com.banking.onlinebanking.dto.TransactionDetailsRequestDto;
import com.banking.onlinebanking.dto.TransactionDetailsResponseDto;
import com.banking.onlinebanking.exceptions.AccountNotActivatedException;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.exceptions.LessThanBankBalanceException;
import com.banking.onlinebanking.service.DepositService;
import com.banking.onlinebanking.service.WithDrawService;
import com.banking.onlinebanking.util.ApplicationConstant;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionDetailsController {
    private final DepositService depositService;
    private final WithDrawService withDrawService;
    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<TransactionDetailsResponseDto>> makeDepositTransaction(@Valid @RequestBody TransactionDetailsRequestDto transactionDetailsRequest)
            throws AccountNumberDoesNotExistsException, AccountNotActivatedException {
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE, depositService
                .makeTransaction(transactionDetailsRequest),null));
    }
    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<TransactionDetailsResponseDto>> makeWithDrawTransaction(@Valid @RequestBody TransactionDetailsRequestDto transactionDetailsRequest)
            throws LessThanBankBalanceException, AccountNumberDoesNotExistsException, AccountNotActivatedException {
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE, withDrawService
                .makeTransaction(transactionDetailsRequest),null));
    }
}
