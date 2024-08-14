package com.banking.onlinebanking.converter;
public interface GenericConverterForEntity<R,E> {
    E convertToEntity(R request);
}
