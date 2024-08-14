package com.banking.onlinebanking.dto;
import com.banking.onlinebanking.customannotations.AgeValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailsRequestDto {
    private String firstName;
    @NotBlank(message = "last name required")
    private String lastName;
    @NotBlank(message = "email required")
    @Email(regexp = "[a-z0-9_.e+%#]+@[a-z]+.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE,message = "enter a valid email address")
    private String email;
    @NotNull(message = "date of birth required")
    @AgeValidator(message = "Age must be at-least 18 years")
    private LocalDate dateOfBirth;
    private AddressDetailsRequestDto address;
    @NotBlank(message = "phone number required")
    @Pattern(regexp = "[0-9]{10}",message = "enter a valid phone number")
    private String phoneNumber;
}
