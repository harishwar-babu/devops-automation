package com.banking.onlinebanking.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:online-banking-configuration.properties")
@ConfigurationProperties(prefix = "bank")
@Data
public class BankConfiguration {
    private int accountNumberLength;
    private String bankCode;
    private int deactivationInterval;
}
