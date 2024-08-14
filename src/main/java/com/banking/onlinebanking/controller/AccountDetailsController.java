package com.banking.onlinebanking.controller;
import com.banking.onlinebanking.dto.AccountDetailsResponseDto;
import com.banking.onlinebanking.dto.ApiResponse;
import com.banking.onlinebanking.exceptions.AccountNumberDoesNotExistsException;
import com.banking.onlinebanking.service.AccountDetailsService;
import com.banking.onlinebanking.util.ApplicationConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class AccountDetailsController {
    private final AccountDetailsService accountDetailsService;

    @PutMapping("/activate/{accountNumber}")
    public ResponseEntity<ApiResponse<String>> activateAccount(@PathVariable String accountNumber)
            throws AccountNumberDoesNotExistsException {
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE,accountDetailsService
                .activateAccount(accountNumber),null));
    }
    @GetMapping("/viewBankAccounts")
    public ResponseEntity<ApiResponse<List<AccountDetailsResponseDto>>> viewBankAccounts(){
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE,accountDetailsService
                .viewBankAccounts(),null));
    }
    @GetMapping("/viewAccountDetail/{accountNumber}")
    public ResponseEntity<ApiResponse<AccountDetailsResponseDto>> viewAccountDetail(@PathVariable String accountNumber)
            throws AccountNumberDoesNotExistsException {
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE,accountDetailsService
                .viewAccountDetail(accountNumber),null));
    }
    @PutMapping("/deactivate/{accountNumber}")
    public ResponseEntity<ApiResponse<String>> deactivateAccount(@PathVariable String accountNumber)
            throws AccountNumberDoesNotExistsException {
        return ResponseEntity.ok(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE,accountDetailsService
                .deActivateAccount(accountNumber),null));
    }

}
