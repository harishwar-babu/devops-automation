package com.banking.onlinebanking.exceptions;

public class LessThanBankBalanceException extends Exception{
    public LessThanBankBalanceException(String message){
        super(message);
    }
}
