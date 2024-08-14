package com.banking.onlinebanking.util;

public enum TransactionTypeUtil {
    DEPOSIT("100"),
    WITHDRAW("101");
    private final String transactionTypeCode;
    TransactionTypeUtil(String transactionTypeCode){
        this.transactionTypeCode = transactionTypeCode;
    }
    public String getTransactionTypeCode(){
        return transactionTypeCode;
    }
}
