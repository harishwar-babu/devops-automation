package com.banking.onlinebanking.configurations;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:error-codes.properties")
@ConfigurationProperties(prefix = "error")
@Data
public class ErrorCodeConfiguration {
    private String validationErrorCode;
    private String recordExistsErrorCode;
    private String invalidAccountNumberErrorCode;
    private String lessBankBalanceErrorCode;
    private String accountNotActivatedErrorCode;
    private String missingPathVariableErrorCode;
}
