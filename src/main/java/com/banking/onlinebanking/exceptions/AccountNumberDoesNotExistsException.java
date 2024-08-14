package com.banking.onlinebanking.exceptions;

public class AccountNumberDoesNotExistsException extends Exception{
    public AccountNumberDoesNotExistsException(String message){
        super(message);
    }
}
