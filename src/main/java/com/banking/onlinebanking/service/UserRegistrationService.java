package com.banking.onlinebanking.service;
import com.banking.onlinebanking.dto.UserDetailsRequestDto;
import com.banking.onlinebanking.dto.UserDetailsResponseDto;
import com.banking.onlinebanking.exceptions.RecordAlreadyExistsException;

public interface UserRegistrationService {
    UserDetailsResponseDto register(UserDetailsRequestDto userDetailsRequest) throws RecordAlreadyExistsException;
}
