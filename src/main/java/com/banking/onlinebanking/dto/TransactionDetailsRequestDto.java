package com.banking.onlinebanking.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TransactionDetailsRequestDto {
    @NotBlank(message = "account number required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{10}$",message = "enter a valid account number")
    private String accountNumber;
    @Min(value = 1, message = "deposit amount should be greater than 1")
    private Double amount;
}
