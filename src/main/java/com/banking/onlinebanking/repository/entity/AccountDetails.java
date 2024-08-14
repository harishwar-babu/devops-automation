package com.banking.onlinebanking.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "accountNumber")
    @GenericGenerator(name = "accountNumber",type = AccountNumberGenerator.class)
    private String accountNumber;
    @Min(value = 0,message = "customer id required")
    private int customerId;
    private double bankBalance;
    @ManyToOne
    @JoinColumn(name = "accountStatus")
    private AccountStatus accountStatus;
    @OneToMany(targetEntity = TransactionDetails.class,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "accountNumber",referencedColumnName = "accountNumber")
    private List<TransactionDetails> transactionDetails;

    @PrePersist
    public void setDefaultBankBalance(){
        this.bankBalance=0.0;
    }

}
