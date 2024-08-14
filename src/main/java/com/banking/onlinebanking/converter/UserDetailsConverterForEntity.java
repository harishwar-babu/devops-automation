package com.banking.onlinebanking.converter;
import com.banking.onlinebanking.dto.UserDetailsRequestDto;
import com.banking.onlinebanking.dto.UserDetailsResponseDto;
import com.banking.onlinebanking.repository.entity.UserDetails;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDetailsConverterForEntity implements GenericConverterForEntity<UserDetailsRequestDto,UserDetails>,
        GenericConverterForResponse<UserDetails,UserDetailsResponseDto> {
    private final ModelMapper modelMapper;
    @Override
    public UserDetailsResponseDto convertToDto(UserDetails entity) {
        return modelMapper.map(entity, UserDetailsResponseDto.class);
    }

    @Override
    public UserDetails convertToEntity(UserDetailsRequestDto request) {
        return modelMapper.map(request, UserDetails.class);
    }
}
