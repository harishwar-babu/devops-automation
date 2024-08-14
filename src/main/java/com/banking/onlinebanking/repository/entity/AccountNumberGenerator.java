package com.banking.onlinebanking.repository.entity;
import com.banking.onlinebanking.configurations.BankConfiguration;
import lombok.AllArgsConstructor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AccountNumberGenerator implements IdentifierGenerator {
    private final BankConfiguration bankConfiguration;
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        StringBuilder accountNumber = new StringBuilder(bankConfiguration.getAccountNumberLength());
        List<Integer> randomNumbers = new ArrayList<>();
        for(int randomNumber=0;randomNumber<=4;randomNumber++){
            randomNumbers.add(randomNumber);
        }
        randomNumbers.subList(0, randomNumbers.size()).forEach(randomNumber->{
            SecureRandom secureRandom = new SecureRandom();
            accountNumber.append(secureRandom.nextInt(10));
        });
        return bankConfiguration.getBankCode()+accountNumber.toString();
    }
}
