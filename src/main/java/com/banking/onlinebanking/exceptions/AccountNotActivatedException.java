package com.banking.onlinebanking.exceptions;

public class AccountNotActivatedException extends Exception{
    public AccountNotActivatedException(String message){
        super(message);
    }
}
