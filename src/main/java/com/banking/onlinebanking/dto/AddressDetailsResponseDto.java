package com.banking.onlinebanking.dto;

import lombok.Data;

@Data
public class AddressDetailsResponseDto {
    private String address;
    private String city;
    private String pinCode;
}
