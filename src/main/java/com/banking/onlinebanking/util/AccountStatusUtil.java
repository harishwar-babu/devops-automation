package com.banking.onlinebanking.util;

public enum AccountStatusUtil {
    APPROVAL_PENDING("200"),
    ACTIVE("201"),
    DEACTIVATED("202");
    private final String currentAccountStatusCode;
    AccountStatusUtil(String currentAccountStatusCode){
        this.currentAccountStatusCode = currentAccountStatusCode;
    }
    public String getCurrentAccountStatusCode(){
        return currentAccountStatusCode;
    }
}
