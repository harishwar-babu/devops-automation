package com.banking.onlinebanking.repository.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountStatus {
    @Id
    private String currentAccountStatusCode;
    private String currentAccountStatus;
}
