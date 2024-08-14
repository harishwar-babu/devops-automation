package com.banking.onlinebanking.converter;

import com.banking.onlinebanking.dto.TransactionDetailsResponseDto;
import com.banking.onlinebanking.repository.entity.TransactionDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TransactionDetailsConverterForDto implements GenericConverterForResponse<TransactionDetails,
        TransactionDetailsResponseDto> {
    private final ModelMapper modelMapper;
    @Override
    public TransactionDetailsResponseDto convertToDto(TransactionDetails entity) {
        return modelMapper.map(entity, TransactionDetailsResponseDto.class);
    }
}
