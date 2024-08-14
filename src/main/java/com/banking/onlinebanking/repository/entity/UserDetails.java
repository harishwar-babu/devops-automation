package com.banking.onlinebanking.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    @OneToOne(targetEntity = AddressDetails.class,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    private AddressDetails address;
    private String phoneNumber;
    @OneToMany(targetEntity = AccountDetails.class,cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "customerId",referencedColumnName = "customerId")
    private List<AccountDetails> accountDetails;

}
