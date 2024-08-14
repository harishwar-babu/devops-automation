package com.banking.onlinebanking.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AddressDetailsRequestDto {
    @NotBlank(message = "address required")
    @Pattern(regexp = "[a-zA-Z]",message = "enter a valid address")
    private String address;
    @NotBlank(message = "city required")
    @Pattern(regexp = "[a-zA-Z]",message = "enter a valid city name")
    private String city;
    @NotBlank(message = "pin code required")
    @Pattern(regexp = "[0-9]{6}",message = "enter a valid pin code")
    private String pinCode;
}
