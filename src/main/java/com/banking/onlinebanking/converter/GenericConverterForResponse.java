package com.banking.onlinebanking.converter;

public interface GenericConverterForResponse <E,D>{
    D convertToDto(E entity);
}
