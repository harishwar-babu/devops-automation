package com.banking.onlinebanking.converter;

import com.banking.onlinebanking.dto.AccountDetailsResponseDto;
import com.banking.onlinebanking.repository.entity.AccountDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountDetailsConverterForDto implements GenericConverterForResponse<AccountDetails, AccountDetailsResponseDto> {
    private final ModelMapper modelMapper;
    @Override
    public AccountDetailsResponseDto convertToDto(AccountDetails entity) {
        return modelMapper.map(entity, AccountDetailsResponseDto.class);
    }
}
