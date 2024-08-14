package com.banking.onlinebanking.controller;
import com.banking.onlinebanking.dto.ApiResponse;
import com.banking.onlinebanking.dto.UserDetailsRequestDto;
import com.banking.onlinebanking.dto.UserDetailsResponseDto;
import com.banking.onlinebanking.exceptions.RecordAlreadyExistsException;
import com.banking.onlinebanking.service.UserRegistrationService;
import com.banking.onlinebanking.util.ApplicationConstant;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserRegistrationController {
    private final UserRegistrationService userRegistrationService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDetailsResponseDto>> register(@Valid @RequestBody UserDetailsRequestDto userDetailsRequest)
            throws RecordAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(ApplicationConstant.SUCCESS_MESSAGE,
                userRegistrationService.register(userDetailsRequest),null));
    }
}
