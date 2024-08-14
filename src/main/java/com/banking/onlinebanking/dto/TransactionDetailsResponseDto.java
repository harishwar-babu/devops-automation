package com.banking.onlinebanking.dto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TransactionDetailsResponseDto {
    private Integer transactionId;
    private LocalDateTime transactionDate;
    private String accountNumber;
    private String transactionType;
    private Double depositAmount;
    private Double withdrawAmount;
}
