package com.banking.onlinebanking.util;
import com.banking.onlinebanking.repository.entity.AccountStatus;
import com.banking.onlinebanking.repository.entity.TransactionTypeDescription;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class JsonParser {
    private final ObjectMapper objectMapper;
    public JsonParser(){
        this.objectMapper = new ObjectMapper();
    }
    public List<TransactionTypeDescription> parseTransactionTypeJsonFile(InputStream inputStream) throws IOException {
        return Arrays.asList(objectMapper.readValue(inputStream, TransactionTypeDescription[].class));
    }
    public List<AccountStatus> parseAccountStatusJsonFile(InputStream inputStream) throws IOException {
        return Arrays.asList(objectMapper.readValue(inputStream,AccountStatus[].class));
    }
}
