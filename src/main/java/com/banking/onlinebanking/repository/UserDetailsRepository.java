package com.banking.onlinebanking.repository;

import com.banking.onlinebanking.repository.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<UserDetails> findByCustomerId(int customerId);
}
