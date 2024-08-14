package com.banking.onlinebanking.configurations;
import com.banking.onlinebanking.repository.AccountStatusRepository;
import com.banking.onlinebanking.repository.entity.AccountStatus;
import com.banking.onlinebanking.repository.entity.TransactionTypeDescription;
import com.banking.onlinebanking.repository.TransactionTypeRepository;
import com.banking.onlinebanking.util.ApplicationConstant;
import com.banking.onlinebanking.util.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DatabaseSaveConfiguration {
    private final ResourceLoader resourceLoader;
    private final TransactionTypeRepository transactionTypeRepository;
    private final AccountStatusRepository accountStatusRepository;

    @Bean
    public CommandLineRunner saveInDataBase(){
        return args -> {
            Resource transactionTypeResource = resourceLoader.getResource(ApplicationConstant.TRANSACTION_TYPE_JSON_CLASS_PATH);
            List<TransactionTypeDescription> transactionTypeDescriptions = parseTransactionTypeJsonFile(transactionTypeResource);
            Resource accountStatusResource = resourceLoader.getResource(ApplicationConstant.ACCOUNT_STATUS_JSON_CLASS_PATH);
            List<AccountStatus> accountStatus = parseAccountStatusJsonFile(accountStatusResource);
            transactionTypeRepository.saveAll(transactionTypeDescriptions);
            accountStatusRepository.saveAll(accountStatus);
        };
    }
    private List<TransactionTypeDescription> parseTransactionTypeJsonFile(Resource resource) throws IOException {
        try(InputStream inputStream = resource.getInputStream()) {
            JsonParser jsonParser = new JsonParser();
            return jsonParser.parseTransactionTypeJsonFile(inputStream);
        }
    }
    private List<AccountStatus> parseAccountStatusJsonFile(Resource resource) throws IOException {
        try(InputStream inputStream = resource.getInputStream()) {
            JsonParser jsonParser = new JsonParser();
            return jsonParser.parseAccountStatusJsonFile(inputStream);
        }
    }
}
