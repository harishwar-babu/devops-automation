package com.banking.onlinebanking.dto;
import lombok.Data;
@Data
public class UserDetailsResponseDto {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private AddressDetailsResponseDto address;
    private String phoneNumber;
    private AccountDetailsResponseDto accountDetails;
}
