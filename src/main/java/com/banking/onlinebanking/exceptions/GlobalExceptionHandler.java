package com.banking.onlinebanking.exceptions;
import com.banking.onlinebanking.configurations.ErrorCodeConfiguration;
import com.banking.onlinebanking.dto.ApiResponse;
import com.banking.onlinebanking.dto.ErrorDetails;
import com.banking.onlinebanking.util.ApplicationConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private final ErrorCodeConfiguration errorCodeConfiguration;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(validationErrors);
    }
    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleRecordAlreadyExistsException(RecordAlreadyExistsException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getRecordExistsErrorCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
    @ExceptionHandler(AccountNumberDoesNotExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleAccountNumberDoesNotExistsException(AccountNumberDoesNotExistsException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getInvalidAccountNumberErrorCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
    @ExceptionHandler(LessThanBankBalanceException.class)
    public ResponseEntity<ApiResponse<String>> handleLessThanBankBalanceException(LessThanBankBalanceException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getRecordExistsErrorCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
}
