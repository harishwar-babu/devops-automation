package com.banking.onlinebanking.exceptions;

public class RecordAlreadyExistsException extends Exception{
    public RecordAlreadyExistsException(String message){
        super(message);
    }
}
