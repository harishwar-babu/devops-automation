package com.banking.onlinebanking.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private T data;
    private ErrorDetails errors;
}
