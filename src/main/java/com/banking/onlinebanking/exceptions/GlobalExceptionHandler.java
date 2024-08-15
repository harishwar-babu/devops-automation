package com.banking.onlinebanking.exceptions;
import com.banking.onlinebanking.configurations.ErrorCodeConfiguration;
import com.banking.onlinebanking.dto.ApiResponse;
import com.banking.onlinebanking.dto.ErrorDetails;
import com.banking.onlinebanking.util.ApplicationConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final ErrorCodeConfiguration errorCodeConfiguration;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> validationErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(),error.getDefaultMessage()));
        String validationErrorString = validationErrors.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", ", "{", "}"));
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getValidationErrorCode(),validationErrorString);
        log.error("validation error : {}",validationErrorString);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
    @ExceptionHandler(RecordAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleRecordAlreadyExistsException(RecordAlreadyExistsException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getRecordExistsErrorCode(), exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
    @ExceptionHandler(AccountNumberDoesNotExistsException.class)
    public ResponseEntity<ApiResponse<String>> handleAccountNumberDoesNotExistsException(AccountNumberDoesNotExistsException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getInvalidAccountNumberErrorCode(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
    @ExceptionHandler(AccountNotActivatedException.class)
    public ResponseEntity<ApiResponse<String>> handleAccountNotActivatedException(AccountNotActivatedException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getAccountNotActivatedErrorCode(), exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
    @ExceptionHandler(LessThanBankBalanceException.class)
    public ResponseEntity<ApiResponse<String>> handleLessThanBankBalanceException(LessThanBankBalanceException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getRecordExistsErrorCode(), exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ApiResponse<String>> handleMissingPathVariableException(MissingPathVariableException exception){
        ErrorDetails errorDetails = new ErrorDetails(errorCodeConfiguration.getMissingPathVariableErrorCode(),exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(ApplicationConstant.FAILURE_MESSAGE,null,errorDetails));
    }
}
