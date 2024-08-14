package com.banking.onlinebanking.service.impl;
import com.banking.onlinebanking.converter.AccountDetailsConverterForDto;
import com.banking.onlinebanking.converter.UserDetailsConverterForEntity;
import com.banking.onlinebanking.dto.UserDetailsRequestDto;
import com.banking.onlinebanking.dto.UserDetailsResponseDto;
import com.banking.onlinebanking.exceptions.RecordAlreadyExistsException;
import com.banking.onlinebanking.repository.AccountDetailsRepository;
import com.banking.onlinebanking.repository.AccountStatusRepository;
import com.banking.onlinebanking.repository.UserDetailsRepository;
import com.banking.onlinebanking.repository.entity.AccountDetails;
import com.banking.onlinebanking.repository.entity.UserDetails;
import com.banking.onlinebanking.service.UserRegistrationService;
import com.banking.onlinebanking.util.AccountStatusUtil;
import com.banking.onlinebanking.util.ApplicationConstant;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsConverterForEntity userDetailsConverter;
    private final AccountStatusRepository accountStatusRepository;
    private final AccountDetailsRepository accountDetailsRepository;
    private final AccountDetailsConverterForDto accountDetailsConverter;
    @Override
    public UserDetailsResponseDto register(UserDetailsRequestDto userDetailsRequest) throws RecordAlreadyExistsException {
        if(userDetailsRepository.existsByPhoneNumber(userDetailsRequest.getPhoneNumber())){
            throw new RecordAlreadyExistsException(ApplicationConstant.RECORD_ALREADY_EXISTS_MESSAGE);
        }
        UserDetails userDetails = userDetailsConverter.convertToEntity(userDetailsRequest);
        userDetailsRepository.save(userDetails);
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setCustomerId(userDetails.getCustomerId());
        accountDetails.setAccountStatus(accountStatusRepository.getReferenceById(AccountStatusUtil.APPROVAL_PENDING
                .getCurrentAccountStatusCode()));
        accountDetailsRepository.save(accountDetails);
        UserDetailsResponseDto userDetailsResponse = userDetailsConverter.convertToDto(userDetails);
        userDetailsResponse.setAccountDetails(accountDetailsConverter.convertToDto(accountDetails));
        return userDetailsResponse;
    }
}
