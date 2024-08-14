package com.banking.onlinebanking.repository.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private LocalDateTime transactionDate;
    private String accountNumber;
    @ManyToOne
    @JoinColumn(name = "transactionTypeDescription")
    private TransactionTypeDescription transactionTypeDescription;
    private Double depositAmount;
    private Double withdrawAmount;
}
