package com.banking.onlinebanking.dto;
import lombok.Data;
import java.util.List;

@Data
public class AccountDetailsResponseDto {
    private String accountNumber;
    private double bankBalance;
    private String accountStatus;
    private List<TransactionDetailsResponseDto> transactionDetails;
}
